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

        Child olderChild = children.stream().max(Comparator.comparing(Child::getAge)).get();
        Child youngestChild = children.stream().min(Comparator.comparing(Child::getAge)).get();

        return Optional.of("Youngest child: " + youngestChild + ", older child: " + olderChild);
    }


    //2. Zwracającą płeć, która ma gorsze średnie wyniki FEV.
    public Optional<Sex> getSexWithWorseAverageFev(){
        if (children.isEmpty()) return Optional.empty();

        double female = getAverageFev(Sex.FEMALE);
        double male = getAverageFev(Sex.MALE);

        if (female > male){
            return Optional.of(Sex.MALE);
        }else return Optional.of(Sex.FEMALE);
    }

    //3. Zwracającą współczynnik procentowy (np 0.5 to 50%) ile dzieci z grupy ma nawyki palacza (smoking habits)
    public Optional<Double> getRatioChildrenSmokers(){
        if (children.isEmpty()) return Optional.empty();

        List<Child> childrenSmokers = getChildrenSmokers(children);
        BigDecimal bd = BigDecimal.valueOf((double) childrenSmokers.size() / children.size());

        return Optional.of(bd.setScale(2, RoundingMode.HALF_UP).doubleValue());
    }

    //4. Drukującą informację (void i wydruk w konsoli) jak wygląda średni wzrost palących chłopców w porównaniu do niepalących;
    public void getAverageHeightAtSmokersBoys(){
        if (children.isEmpty()) {
            System.out.println("Children list is null");
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
    public Optional<Child[]> getMalesWhoHaveSmokes(){
        if (children.isEmpty()) return Optional.empty();
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
        }return Optional.of(result);
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

}
