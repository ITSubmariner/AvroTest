/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package ru.samples.kafkasample.dto.avro;
@org.apache.avro.specific.AvroGenerated
public enum Status implements org.apache.avro.generic.GenericEnumSymbol<Status> {
  CREATED, IN_PROCESS, DONE  ;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"enum\",\"name\":\"Status\",\"namespace\":\"ru.samples.kafkasample.dto.avro\",\"symbols\":[\"CREATED\",\"IN_PROCESS\",\"DONE\"]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
}
