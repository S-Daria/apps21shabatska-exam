package domain;

import json.*;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    protected ArrayList<Tuple<String, Integer>> exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = new ArrayList<>();
        Collections.addAll(this.exams, exams);
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject student = super.toJsonObject();
        JsonObject[] examsResults = new JsonObject[this.exams.size()];

        for(int i = 0 ; i < this.exams.size(); i++){
            Tuple<String, Integer> exam = this.exams.get(i);
            examsResults[i] = new JsonObject(
                    new JsonPair("course", new JsonString(exam.key)),
                    new JsonPair("mark", new JsonNumber(exam.value)),
                    new JsonPair("passed", new JsonBoolean(exam.value > 2))
            );
        }
        student.add(new JsonPair("exams", new JsonArray(examsResults)));
        return student;
    }
}