package pl.homework.service;

import pl.homework.models.Child;
import pl.homework.models.Sex;
import pl.homework.models.Smoke;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ChildrenReader {

    public static List<Child> read(String fileName){
        List<Child> children = new ArrayList<>();
        try (
                FileReader fr = new FileReader(fileName);
                BufferedReader br = new BufferedReader(fr)
                ){
            if (br.ready()) br.readLine();
            String line = null;
            while ((line = br.readLine()) != null){
                children.add(getChildren(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }return children;
    }

    private static Child getChildren(String line) {
        String[] split = line.split(",");
        int seqnbr = Integer.parseInt(split[0]);
        int subjid = Integer.parseInt(split[1]);
        int age = Integer.parseInt(split[2]);
        double fev = Double.parseDouble(split[3]);
        double height = Double.parseDouble(split[4]);
        Sex sex = checkSex(Integer.parseInt(split[5]));
        Smoke smoke = isSmoker(Integer.parseInt(split[6]));
        return new Child(seqnbr, subjid, age, fev, height, sex, smoke);
    }

    private static Sex checkSex(int number){
        if (number == 1){
            return Sex.MALE;
        }else return Sex.FEMALE;
    }

    private static Smoke isSmoker(int number){
        if (number == 1){
            return Smoke.YES;
        }else return Smoke.NO;
    }

}
