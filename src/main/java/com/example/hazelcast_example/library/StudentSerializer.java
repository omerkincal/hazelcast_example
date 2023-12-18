package com.example.hazelcast_example.library;


import com.example.hazelcast_example.student.impl.Student;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;

import java.io.IOException;

public class StudentSerializer implements StreamSerializer<Student> {

    @Override
    public int getTypeId() {
        return 1; // Her nesne tipi için benzersiz bir tür numarası
    }

    @Override
    public void write(ObjectDataOutput out, Student student) throws IOException {
        out.writeUTF(student.getFirstName());
        out.writeUTF(student.getLastName());
        out.writeInt(student.getAge());
    }

    @Override
    public Student read(ObjectDataInput in) throws IOException {
        String firstName = in.readUTF();
        String lastName = in.readUTF();
        int age = in.readInt();
        return new Student(firstName, lastName, age);
    }

    @Override
    public void destroy() {
        // Serileştirici ömrü boyunca yapılması gereken temizlik işlemleri
    }
}
