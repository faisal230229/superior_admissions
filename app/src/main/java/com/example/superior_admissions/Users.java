package com.example.superior_admissions;

public class Users {
    String Name,fname, email, mobile, cic,fcic,matric2,inter2,selectDegree, pas, program, college, regis, fee;

    public Users(String name, String fname, String email, String mobile, String cic, String fcic, String matric2, String inter2, String selectDegree, String pas, String program, String college, String regis, String fee) {
        Name = name;
        this.fname = fname;
        this.email = email;
        this.mobile = mobile;
        this.cic = cic;
        this.fcic = fcic;
        this.matric2 = matric2;
        this.inter2 = inter2;
        this.selectDegree = selectDegree;
        this.pas = pas;
        this.program = program;
        this.college = college;
        this.regis = regis;
        this.fee = fee;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCic() {
        return cic;
    }

    public void setCic(String cic) {
        this.cic = cic;
    }

    public String getFcic() {
        return fcic;
    }

    public void setFcic(String fcic) {
        this.fcic = fcic;
    }

    public String getMatric2() {
        return matric2;
    }

    public void setMatric2(String matric2) {
        this.matric2 = matric2;
    }

    public String getInter2() {
        return inter2;
    }

    public void setInter2(String inter2) {
        this.inter2 = inter2;
    }

    public String getSelectDegree() {
        return selectDegree;
    }

    public void setSelectDegree(String selectDegree) {
        this.selectDegree = selectDegree;
    }

    public String getPas() {
        return pas;
    }

    public void setPas(String pas) {
        this.pas = pas;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getRegis() {
        return regis;
    }

    public void setRegis(String regis) {
        this.regis = regis;
    }

    public  String  getFee(){ return  fee; }

    public void setFee(String fee) {
        this.fee = fee;
    }
}
