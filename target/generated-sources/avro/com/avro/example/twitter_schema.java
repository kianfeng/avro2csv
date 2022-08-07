/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.avro.example;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class twitter_schema extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -4470547180558504585L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"twitter_schema\",\"namespace\":\"com.avro.example\",\"fields\":[{\"name\":\"username\",\"type\":\"string\",\"doc\":\"Name of the user account on Twitter.com\"},{\"name\":\"tweet\",\"type\":\"string\",\"doc\":\"The content of the user's Twitter message\"},{\"name\":\"timestamp\",\"type\":\"long\",\"doc\":\"Unix epoch time in seconds\"}],\"doc:\":\"A basic schema for storing Twitter messages\"}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<twitter_schema> ENCODER =
      new BinaryMessageEncoder<twitter_schema>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<twitter_schema> DECODER =
      new BinaryMessageDecoder<twitter_schema>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<twitter_schema> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<twitter_schema> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<twitter_schema> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<twitter_schema>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this twitter_schema to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a twitter_schema from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a twitter_schema instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static twitter_schema fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** Name of the user account on Twitter.com */
  private java.lang.CharSequence username;
  /** The content of the user's Twitter message */
  private java.lang.CharSequence tweet;
  /** Unix epoch time in seconds */
  private long timestamp;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public twitter_schema() {}

  /**
   * All-args constructor.
   * @param username Name of the user account on Twitter.com
   * @param tweet The content of the user's Twitter message
   * @param timestamp Unix epoch time in seconds
   */
  public twitter_schema(java.lang.CharSequence username, java.lang.CharSequence tweet, java.lang.Long timestamp) {
    this.username = username;
    this.tweet = tweet;
    this.timestamp = timestamp;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return username;
    case 1: return tweet;
    case 2: return timestamp;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: username = (java.lang.CharSequence)value$; break;
    case 1: tweet = (java.lang.CharSequence)value$; break;
    case 2: timestamp = (java.lang.Long)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'username' field.
   * @return Name of the user account on Twitter.com
   */
  public java.lang.CharSequence getUsername() {
    return username;
  }


  /**
   * Sets the value of the 'username' field.
   * Name of the user account on Twitter.com
   * @param value the value to set.
   */
  public void setUsername(java.lang.CharSequence value) {
    this.username = value;
  }

  /**
   * Gets the value of the 'tweet' field.
   * @return The content of the user's Twitter message
   */
  public java.lang.CharSequence getTweet() {
    return tweet;
  }


  /**
   * Sets the value of the 'tweet' field.
   * The content of the user's Twitter message
   * @param value the value to set.
   */
  public void setTweet(java.lang.CharSequence value) {
    this.tweet = value;
  }

  /**
   * Gets the value of the 'timestamp' field.
   * @return Unix epoch time in seconds
   */
  public long getTimestamp() {
    return timestamp;
  }


  /**
   * Sets the value of the 'timestamp' field.
   * Unix epoch time in seconds
   * @param value the value to set.
   */
  public void setTimestamp(long value) {
    this.timestamp = value;
  }

  /**
   * Creates a new twitter_schema RecordBuilder.
   * @return A new twitter_schema RecordBuilder
   */
  public static com.avro.example.twitter_schema.Builder newBuilder() {
    return new com.avro.example.twitter_schema.Builder();
  }

  /**
   * Creates a new twitter_schema RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new twitter_schema RecordBuilder
   */
  public static com.avro.example.twitter_schema.Builder newBuilder(com.avro.example.twitter_schema.Builder other) {
    if (other == null) {
      return new com.avro.example.twitter_schema.Builder();
    } else {
      return new com.avro.example.twitter_schema.Builder(other);
    }
  }

  /**
   * Creates a new twitter_schema RecordBuilder by copying an existing twitter_schema instance.
   * @param other The existing instance to copy.
   * @return A new twitter_schema RecordBuilder
   */
  public static com.avro.example.twitter_schema.Builder newBuilder(com.avro.example.twitter_schema other) {
    if (other == null) {
      return new com.avro.example.twitter_schema.Builder();
    } else {
      return new com.avro.example.twitter_schema.Builder(other);
    }
  }

  /**
   * RecordBuilder for twitter_schema instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<twitter_schema>
    implements org.apache.avro.data.RecordBuilder<twitter_schema> {

    /** Name of the user account on Twitter.com */
    private java.lang.CharSequence username;
    /** The content of the user's Twitter message */
    private java.lang.CharSequence tweet;
    /** Unix epoch time in seconds */
    private long timestamp;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.avro.example.twitter_schema.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.username)) {
        this.username = data().deepCopy(fields()[0].schema(), other.username);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.tweet)) {
        this.tweet = data().deepCopy(fields()[1].schema(), other.tweet);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[2].schema(), other.timestamp);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
    }

    /**
     * Creates a Builder by copying an existing twitter_schema instance
     * @param other The existing instance to copy.
     */
    private Builder(com.avro.example.twitter_schema other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.username)) {
        this.username = data().deepCopy(fields()[0].schema(), other.username);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.tweet)) {
        this.tweet = data().deepCopy(fields()[1].schema(), other.tweet);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[2].schema(), other.timestamp);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'username' field.
      * Name of the user account on Twitter.com
      * @return The value.
      */
    public java.lang.CharSequence getUsername() {
      return username;
    }


    /**
      * Sets the value of the 'username' field.
      * Name of the user account on Twitter.com
      * @param value The value of 'username'.
      * @return This builder.
      */
    public com.avro.example.twitter_schema.Builder setUsername(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.username = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'username' field has been set.
      * Name of the user account on Twitter.com
      * @return True if the 'username' field has been set, false otherwise.
      */
    public boolean hasUsername() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'username' field.
      * Name of the user account on Twitter.com
      * @return This builder.
      */
    public com.avro.example.twitter_schema.Builder clearUsername() {
      username = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'tweet' field.
      * The content of the user's Twitter message
      * @return The value.
      */
    public java.lang.CharSequence getTweet() {
      return tweet;
    }


    /**
      * Sets the value of the 'tweet' field.
      * The content of the user's Twitter message
      * @param value The value of 'tweet'.
      * @return This builder.
      */
    public com.avro.example.twitter_schema.Builder setTweet(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.tweet = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'tweet' field has been set.
      * The content of the user's Twitter message
      * @return True if the 'tweet' field has been set, false otherwise.
      */
    public boolean hasTweet() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'tweet' field.
      * The content of the user's Twitter message
      * @return This builder.
      */
    public com.avro.example.twitter_schema.Builder clearTweet() {
      tweet = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'timestamp' field.
      * Unix epoch time in seconds
      * @return The value.
      */
    public long getTimestamp() {
      return timestamp;
    }


    /**
      * Sets the value of the 'timestamp' field.
      * Unix epoch time in seconds
      * @param value The value of 'timestamp'.
      * @return This builder.
      */
    public com.avro.example.twitter_schema.Builder setTimestamp(long value) {
      validate(fields()[2], value);
      this.timestamp = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'timestamp' field has been set.
      * Unix epoch time in seconds
      * @return True if the 'timestamp' field has been set, false otherwise.
      */
    public boolean hasTimestamp() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'timestamp' field.
      * Unix epoch time in seconds
      * @return This builder.
      */
    public com.avro.example.twitter_schema.Builder clearTimestamp() {
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public twitter_schema build() {
      try {
        twitter_schema record = new twitter_schema();
        record.username = fieldSetFlags()[0] ? this.username : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.tweet = fieldSetFlags()[1] ? this.tweet : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.timestamp = fieldSetFlags()[2] ? this.timestamp : (java.lang.Long) defaultValue(fields()[2]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<twitter_schema>
    WRITER$ = (org.apache.avro.io.DatumWriter<twitter_schema>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<twitter_schema>
    READER$ = (org.apache.avro.io.DatumReader<twitter_schema>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.username);

    out.writeString(this.tweet);

    out.writeLong(this.timestamp);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.username = in.readString(this.username instanceof Utf8 ? (Utf8)this.username : null);

      this.tweet = in.readString(this.tweet instanceof Utf8 ? (Utf8)this.tweet : null);

      this.timestamp = in.readLong();

    } else {
      for (int i = 0; i < 3; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.username = in.readString(this.username instanceof Utf8 ? (Utf8)this.username : null);
          break;

        case 1:
          this.tweet = in.readString(this.tweet instanceof Utf8 ? (Utf8)this.tweet : null);
          break;

        case 2:
          this.timestamp = in.readLong();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










