package com.supos.eco.vo.response;

/**
 * @author caonuoqi@supos.com
 * @date 2022/7/19
 */
public class User {
    private String username;

    private String userDesc;

    private int accountType;

    private int lockStatus;

    private String personCode;

    private String personName;

    private String createTime;

    private String modifyTime;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public String getUserDesc() {
        return this.userDesc;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public int getAccountType() {
        return this.accountType;
    }

    public void setLockStatus(int lockStatus) {
        this.lockStatus = lockStatus;
    }

    public int getLockStatus() {
        return this.lockStatus;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public String getPersonCode() {
        return this.personCode;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonName() {
        return this.personName;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyTime() {
        return this.modifyTime;
    }

}
