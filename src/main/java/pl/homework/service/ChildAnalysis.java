package pl.homework.service;

import pl.homework.models.Child;
import pl.homework.models.Sex;
import pl.homework.models.Smoke;
import pl.homework.models.YoungestAndOlderChild;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
    public Optional<YoungestAndOlderChild> getOlderAndYoungestChildren(Sex sex){
        if (children.isEmpty()) return Optional.empty();

        List<Child> childrenOfOneSex = getChildrenOfOneSex(sex);

        Optional<Child> olderChild = childrenOfOneSex.stream().max(Comparator.comparing(Child::getAge));
        Optional<Child> youngestChild = childrenOfOneSex.stream().min(Comparator.comparing(Child::getAge));

        if (olderChild.isEmpty() && youngestChild.isEmpty()) return Optional.empty();
        return Optional.of(new YoungestAndOlderChild(youngestChild.get(), olderChild.get()));
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

        List<Child> childrenSmokers = getChildrenSmokers();
        BigDecimal bd = BigDecimal.valueOf((double) childrenSmokers.size() / children.size());

        return Optional.of(bd.setScale(2, RoundingMode.HALF_UP).doubleValue());
    }

    private List<Child> getChildrenSmokers() {
        List<Child> childrenSmokers = new ArrayList<>();

        for (Child child : children) {
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
