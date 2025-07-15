package pl.homework.service;

import pl.homework.models.Child;
import pl.homework.models.Sex;
import pl.homework.models.Smoke;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class ChildAnalysis {

    private final List<Child> children;

    public ChildAnalysis(List<Child> children) {
        this.children = getNonNullchildrenList(children);
    }

    private List<Child> getNonNullchildrenList(List<Child> children) {
        List<Child> nonNullChildrenList = new ArrayList<>();
        for (Child child : children) {
            if (child != null){
                nonNullChildrenList.add(child);
            }
        }return nonNullChildrenList;
    }

    //1. Drukującą informację o najstarszym i najmłodszym dziecku biorącym udział w badaniu (może być void i wydruk do konsoli)
    public Optional<String> getOlderAndYoungestChildren(){
        if (children.isEmpty()) return Optional.empty();


        /// theOldestChild
        Child olderChild = getTheOldestChild();
        /// theYoungestChild
        Child youngestChild = getTheYoungestChild();

        return Optional.of("Youngest child: " + youngestChild + ", older child: " + olderChild);
    }
    /// napisz dwie prywatne metody obi emaja zwracac child tylko jedna najstarsze a jedna najmlodsze


    //2. Zwracającą płeć, która ma gorsze średnie wyniki FEV.
    public Sex getSexWithWorseAverageFev(){
        if (children.isEmpty()) return null;

        double femalesFev = getAverageFev(Sex.FEMALE);
        double malesFev = getAverageFev(Sex.MALE);

        if (femalesFev > malesFev){
            return Sex.MALE;
        }else return Sex.FEMALE;
    }

    //3. Zwracającą współczynnik procentowy (np 0.5 to 50%) ile dzieci z grupy ma nawyki palacza (smoking habits)
    public BigDecimal getRatioChildrenSmokers(){
        if (children.isEmpty()) return BigDecimal.ZERO;

        List<Child> childrenSmokers = getChildrenSmokers(children);
        BigDecimal bd = BigDecimal.valueOf((double) childrenSmokers.size() / children.size());

        return bd.setScale(2, RoundingMode.HALF_UP);
    }

    //4. Drukującą informację (void i wydruk w konsoli) jak wygląda średni wzrost palących chłopców w porównaniu do niepalących;
    public void getAverageHeightAtSmokersBoys(){
        if (children.isEmpty()) {
            System.out.println("Children list is empty");
        }else {
            List<Child> childrenSmokers = getChildrenSmokers(getChildrenOfOneSex(Sex.MALE));
            double sum = 0;
            for (Child childrenSmoker : childrenSmokers) {
                sum += childrenSmoker.getHeight();
            }
            System.out.println("Average smokers boys height: " + sum / childrenSmokers.size());
        }
    }
    //5. To samo co wyżej tylko wersja dla dziewczynek.
    public void getAverageHeightAtSmokersGirls(){
        if (children.isEmpty()) {
            System.out.println("Children list is null");
        }else {
            List<Child> childrenSmokers = getChildrenSmokers(getChildrenOfOneSex(Sex.FEMALE));
            double sum = 0;
            for (Child childrenSmoker : childrenSmokers) {
                sum += childrenSmoker.getHeight();
            }
            System.out.println("Average smokers girls height: " + sum / childrenSmokers.size());
        }
    }
    //6. Zwracającą najstarsze dziecko biorące udział w badaniu.
    public Optional<Child> getOldestChild() {
        return children.stream().max(Comparator.comparing(Child::getAge));
    }

    //7. Zwracającą tablicę chłopców, którzy mają nawyki palacza.
    public Child[] getSmokingBoys(){
        if (children.isEmpty()) return new Child[0];

        List<Child> childrenOfOneSex = getChildrenOfOneSex(Sex.MALE);
        List<Child> malesSmokers = new ArrayList<>();
        for (Child tmp : childrenOfOneSex) {
            if(tmp.getSmoke() == Smoke.YES){
                malesSmokers.add(tmp);
            }
        }

        Child[] result = new Child[malesSmokers.size()];
        for (int i = 0; i < malesSmokers.size(); i++) {
            result[i] = malesSmokers.get(i);
        }return result;
    }

    private List<Child> getChildrenSmokers(List<Child> childrenOfOneSex) {
        List<Child> childrenSmokers = new ArrayList<>();

        for (Child child : childrenOfOneSex) {
            if (child.getSmoke() == Smoke.YES){
                childrenSmokers.add(child);
            }
        }
        return childrenSmokers;
    }

    private double getAverageFev(Sex sex) {
        List<Child> childrenOfOneSex = getChildrenOfOneSex(sex);
        double sum = 0;
        for (Child child : children) {
            sum += child.getFev();
        }
        return sum / childrenOfOneSex.size();
    }


    private List<Child> getChildrenOfOneSex(Sex sex){
        List<Child> childrenOfOneSex = new ArrayList<>();
        for (Child child : children) {
            if (child.getSex() == sex){
                childrenOfOneSex.add(child);
            }
        }return childrenOfOneSex;
    }

    private Child getTheOldestChild(){
        Child theOldestChild = children.get(0);
        int maxAge = theOldestChild.getAge();
        for (Child child : children) {
            int currentAge = child.getAge();
            if (maxAge < currentAge){
                theOldestChild = child;
                maxAge = currentAge;
            }
        }return theOldestChild;
    }

    private Child getTheYoungestChild(){
        Child theYoungestChild = children.get(0);
        int minAge = theYoungestChild.getAge();
        for (Child child : children) {
            int currentAge = child.getAge();
            if (minAge > currentAge){
                theYoungestChild = child;
                minAge = currentAge;
            }
        }return theYoungestChild;
    }
}
