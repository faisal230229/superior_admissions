package com.example.superior_admissions;

public class Users {
    String Name, email, mobile, cic,fcic,matric2,inter2,selectDegree, pas, program;

    public Users() {
    }


    public Users(String name, String email, String mobile, String cic, String fcic, String matric2, String inter2, String selectDegree, String pas, String program) {
        Name = name;
        this.email = email;
        this.mobile = mobile;
        this.cic = cic;
        this.fcic = fcic;
        this.matric2 = matric2;
        this.inter2 = inter2;
        this.selectDegree = selectDegree;
        this.pas = pas;
        this.program = program;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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
}
