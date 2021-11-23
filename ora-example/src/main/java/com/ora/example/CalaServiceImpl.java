package com.ora.example;

/**
 * @author wzy
 * @version 1.0
 * @description: TODO
 * @date 2021/11/18 14:27
 */
public class CalaServiceImpl implements CalaService{
    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int minus(int a, int b) {
        return a-b;
    }
}
