package com.greta.angkasanew.Model;

import java.util.List;

public class UserResponse {

    private int code;
    private String status;
    private List<UserModel> user_list;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<UserModel> getUser_list() {
        return user_list;
    }

    public void setUser_list(List<UserModel> user_list) {
        this.user_list = user_list;
    }
}
