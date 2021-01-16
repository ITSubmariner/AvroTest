package org.pet.avro;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.samples.kafkasample.dto.avro.RegisteredEventAvro;
import ru.samples.kafkasample.dto.avro.Status;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication
public class AvroApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(AvroApplication.class, args);
        RegisteredEventAvro registeredEventAvro = new RegisteredEventAvro();
        registeredEventAvro.setName("name");
        registeredEventAvro.setStatus(Status.DONE);
        byte[] data = serialize(registeredEventAvro);
        deserialize(data);
    }

    private static byte[] serialize(RegisteredEventAvro registeredEventAvro) {
        DatumWriter<RegisteredEventAvro> writer = new SpecificDatumWriter<>(RegisteredEventAvro.class);
        byte[] data = new byte[0];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Encoder jsonEncoder;
        try {
            jsonEncoder = EncoderFactory.get().jsonEncoder(RegisteredEventAvro.getClassSchema(), stream);
            writer.write(registeredEventAvro, jsonEncoder);
            jsonEncoder.flush();
            System.out.println(stream.toString());
            data = stream.toByteArray();
        } catch (IOException e) {
            System.out.println("Serialization error:" + e.getMessage());
        }
//        System.out.println(data.toString());
        return data;
    }

    private static void deserialize(byte[] source) throws IOException {
        DatumReader<RegisteredEventAvro> reader = new SpecificDatumReader<>(RegisteredEventAvro.class);
        Decoder decoder;
        try {
            decoder = DecoderFactory.get().jsonDecoder(RegisteredEventAvro.getClassSchema(), new String(source));
            System.out.println(reader.read(null, decoder));
        } catch (IOException e) {
            System.out.println("Deserialization error:" + e.getMessage());
        }
    }

}
