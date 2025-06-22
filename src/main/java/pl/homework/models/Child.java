package pl.homework.models;

import java.util.Objects;

public class Child {
    private int seqnbr;
    private int subjid;
    private int age;
    private double fev;
    private double height;
    private Sex sex;
    private Smoke smoke;

    public Child(int seqnbr, int subjid, int age, double fev, double height, Sex sex, Smoke smoke) {
        this.seqnbr = seqnbr;
        this.subjid = subjid;
        this.age = age;
        this.fev = fev;
        this.height = height;
        this.sex = sex;
        this.smoke = smoke;
    }

    public int getSeqnbr() {
        return seqnbr;
    }

    public void setSeqnbr(int seqnbr) {
        this.seqnbr = seqnbr;
    }

    public int getSubjid() {
        return subjid;
    }

    public void setSubjid(int subjid) {
        this.subjid = subjid;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getFev() {
        return fev;
    }

    public void setFev(double fev) {
        this.fev = fev;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Smoke getSmoke() {
        return smoke;
    }

    public void setSmoke(Smoke smoke) {
        this.smoke = smoke;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Child child = (Child) o;
        return seqnbr == child.seqnbr && subjid == child.subjid && age == child.age && Double.compare(fev, child.fev) == 0 && Double.compare(height, child.height) == 0 && sex == child.sex && smoke == child.smoke;
    }

    @Override
    public int hashCode() {
        return Objects.hash(seqnbr, subjid, age, fev, height, sex, smoke);
    }

    @Override
    public String toString() {
        return "Child{" +
                "seqnbr=" + seqnbr +
                ", subjid=" + subjid +
                ", age=" + age +
                ", fev=" + fev +
                ", height=" + height +
                ", sex=" + sex +
                ", smoke=" + smoke +
                '}';
    }
}
