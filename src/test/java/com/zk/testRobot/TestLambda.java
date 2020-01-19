package com.zk.testRobot;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestLambda {

    @Test
    public void test1(){
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(i);
            user.setUserName("un" + i);
            user.setPassword("password" + i);
            userList.add(user);
        }
        // 通过Lambda取出User里面id的值
        long t1 = System.currentTimeMillis();
        List<Integer> idList_LB = userList.stream().map(user -> user.getId()).collect(Collectors.toList());
        System.out.println("lambda:"+(System.currentTimeMillis() - t1));
        // 普通方法
        long t2 = System.currentTimeMillis();
        List<Integer> idList_PT = new ArrayList<>();
        for (User user : userList) {
            idList_PT.add(user.getId());
        }
        System.out.println("foreach:"+(System.currentTimeMillis() - t2));
    }

    @Test
    public void test2(){
        User[] users = null;
        for (User user:users){//增强for循环的遍历对象和数组不能为空，因为对于集合的foreach遍历时利用了集合的iterator()迭代器遍历，而数组是利用
            //数组的length长度遍历，所以都不能为空
            System.out.println("test");
        }
    }
    class User {
        Integer id;
        String userName;
        String password;
        public Integer getId() {
            return id;
        }
        public void setId(Integer id) {
            this.id = id;
        }
        public String getUserName() {
            return userName;
        }
        public void setUserName(String userName) {
            this.userName = userName;
        }
        public String getPassword(){
            return password;
        }
        public void setPassword(String password){
            this.password = password;
        }
    }
}

