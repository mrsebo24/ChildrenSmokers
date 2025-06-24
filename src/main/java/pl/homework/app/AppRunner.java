package pl.homework.app;

import pl.homework.models.Child;
import pl.homework.service.ChildAnalysis;
import pl.homework.service.ChildrenReader;
import java.util.List;

public class AppRunner {
    public static void main(String[] args) {

        List<Child> read = ChildrenReader.read("dzieci_palacze/dzieci_palacze.txt");

        ChildAnalysis childAnalysis = new ChildAnalysis(read);
    }
}
