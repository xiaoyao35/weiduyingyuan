package com.bw.movie.model;

/**
 * author: 韩聪聪
 * data: 2019/11/5 09:9:40
 * function:
 */
public class Result<T> {
    public String message;
    public String status;
    public T result;

    public Result(String message) {
        this.message = message;
    }
}
