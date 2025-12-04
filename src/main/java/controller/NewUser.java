package controller;

import model.Student;

public interface NewUser {
    void save(Student student);

    String getLastId();
}
