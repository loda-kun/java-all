package me.loda.java.threadpool;
/*******************************************************
 * For Vietnamese readers:
 *    Các bạn thân mến, mình rất vui nếu project này giúp 
 * ích được cho các bạn trong việc học tập và công việc. Nếu 
 * bạn sử dụng lại toàn bộ hoặc một phần source code xin để 
 * lại dường dẫn tới github hoặc tên tác giá.
 *    Xin cảm ơn!
 *******************************************************/

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 4/9/2019
 * Github: https://github.com/loda-kun
 */
public class FixedThreadPoolExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Có 100 request tới cùng lúc

        for (int i = 0; i < 100; i++) {
            executor.execute(new RequestHandler("request-" + i));
        }
        executor.shutdown(); // Không cho threadpool nhận thêm nhiệm vụ nào nữa

        while (!executor.isTerminated()) {
            // Chờ xử lý hết các request còn chờ trong Queue ...
        }
    }
}
