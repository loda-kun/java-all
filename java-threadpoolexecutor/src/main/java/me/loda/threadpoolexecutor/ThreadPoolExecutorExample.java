package me.loda.threadpoolexecutor;
/*******************************************************
 * For Vietnamese readers:
 *    Các bạn thân mến, mình rất vui nếu project này giúp 
 * ích được cho các bạn trong việc học tập và công việc. Nếu 
 * bạn sử dụng lại toàn bộ hoặc một phần source code xin để 
 * lại dường dẫn tới github hoặc tên tác giá.
 *    Xin cảm ơn!
 *******************************************************/

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 4/9/2019
 * Github: https://github.com/loda-kun
 */
public class ThreadPoolExecutorExample {
    public static void main(String[] args) throws InterruptedException {
        int corePoolSize = 5;
        int maximumPoolSize = 10;
        int queueCapacity = 100;
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, // Số corePoolSize
                                                             maximumPoolSize, // số maximumPoolSize
                                                             10, // thời gian một thread được sống nếu không làm gì
                                                             TimeUnit.SECONDS,
                                                             new ArrayBlockingQueue<>(queueCapacity)); // Blocking queue để cho request đợi
        // 1000 request đến dồn dập, liền 1 phát, không nghỉ
        for (int i = 0; i < 1000; i++) {
            executor.execute(new RequestHandler("request-" + i));
            Thread.sleep(50);
        }
//        executor.shutdown(); // Không cho threadpool nhận thêm nhiệm vụ nào nữa

        while (!executor.isTerminated()) {
            // Chờ xử lý hết các request còn chờ trong Queue ...
        }
    }
}
