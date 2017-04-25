package com.moment.user.domain;

import java.io.Serializable;
import java.util.Date;

public class UserVO implements Serializable {
    private Integer id;

    private String sex;

    private String name;

    private String phonum;

    private String email;

    private String salt;

    private String password;

    private Date time;

    private Integer score;

    private Integer picnum;

    private Integer collectnum;

    private Integer collectpicnum;

    private Integer fansnum;

    private Integer concernnum;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhonum() {
        return phonum;
    }

    public void setPhonum(String phonum) {
        this.phonum = phonum == null ? null : phonum.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getPicnum() {
        return picnum;
    }

    public void setPicnum(Integer picnum) {
        this.picnum = picnum;
    }

    public Integer getCollectnum() {
        return collectnum;
    }

    public void setCollectnum(Integer collectnum) {
        this.collectnum = collectnum;
    }

    public Integer getCollectpicnum() {
        return collectpicnum;
    }

    public void setCollectpicnum(Integer collectpicnum) {
        this.collectpicnum = collectpicnum;
    }

    public Integer getFansnum() {
        return fansnum;
    }

    public void setFansnum(Integer fansnum) {
        this.fansnum = fansnum;
    }

    public Integer getConcernnum() {
        return concernnum;
    }

    public void setConcernnum(Integer concernnum) {
        this.concernnum = concernnum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sex=").append(sex);
        sb.append(", name=").append(name);
        sb.append(", phonum=").append(phonum);
        sb.append(", email=").append(email);
        sb.append(", salt=").append(salt);
        sb.append(", password=").append(password);
        sb.append(", time=").append(time);
        sb.append(", score=").append(score);
        sb.append(", picnum=").append(picnum);
        sb.append(", collectnum=").append(collectnum);
        sb.append(", collectpicnum=").append(collectpicnum);
        sb.append(", fansnum=").append(fansnum);
        sb.append(", concernnum=").append(concernnum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UserVO other = (UserVO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPhonum() == null ? other.getPhonum() == null : this.getPhonum().equals(other.getPhonum()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getSalt() == null ? other.getSalt() == null : this.getSalt().equals(other.getSalt()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()))
            && (this.getPicnum() == null ? other.getPicnum() == null : this.getPicnum().equals(other.getPicnum()))
            && (this.getCollectnum() == null ? other.getCollectnum() == null : this.getCollectnum().equals(other.getCollectnum()))
            && (this.getCollectpicnum() == null ? other.getCollectpicnum() == null : this.getCollectpicnum().equals(other.getCollectpicnum()))
            && (this.getFansnum() == null ? other.getFansnum() == null : this.getFansnum().equals(other.getFansnum()))
            && (this.getConcernnum() == null ? other.getConcernnum() == null : this.getConcernnum().equals(other.getConcernnum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPhonum() == null) ? 0 : getPhonum().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getSalt() == null) ? 0 : getSalt().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
        result = prime * result + ((getPicnum() == null) ? 0 : getPicnum().hashCode());
        result = prime * result + ((getCollectnum() == null) ? 0 : getCollectnum().hashCode());
        result = prime * result + ((getCollectpicnum() == null) ? 0 : getCollectpicnum().hashCode());
        result = prime * result + ((getFansnum() == null) ? 0 : getFansnum().hashCode());
        result = prime * result + ((getConcernnum() == null) ? 0 : getConcernnum().hashCode());
        return result;
    }
}