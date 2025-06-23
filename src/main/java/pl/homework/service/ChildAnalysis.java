package pl.homework.service;

import pl.homework.models.Child;
import java.util.ArrayList;
import java.util.List;

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


}
