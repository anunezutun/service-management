package com.anthares.commons.guid;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

/** Class.
 *
 * @author abelK
 */
public class GeneratorGuid implements IdentifierGenerator {

  private static final Integer TENANT_NUMBER = 1;
  private static final Integer CONTEXT_NUMBER = 99;
  private static final char[] DICTIONARY_62 = getDictionary62();


  private static final int NODE_ID_BITS = 12;
  private static final int SEQUENCE_BITS = 10;
  private static final long MAX_NODE_ID = (1L << NODE_ID_BITS) - 1;
  private static final long MAX_SEQUENCE = (1L << SEQUENCE_BITS) - 1;
  private static final long DEFAULT_CUSTOM_EPOCH = 1609459200000L;
  private static final long NODE_ID = getNodeId();
  private static final long CUSTOM_EPOCH = DEFAULT_CUSTOM_EPOCH;
  private volatile long lastTimestamp = -1L;
  private volatile long sequence = 0L;

  private static char[] getDictionary62() {
    return  new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
                       'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                       'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                       'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                       'y', 'z'};
  }

  @Override
  public Serializable generate(SharedSessionContractImplementor session, Object object)
          throws HibernateException {
    return generateAlphaId();
  }

  /**
   * Generates a new guid.
   *
   * @return new guid.
   */
  public synchronized String generateAlphaId() {
    if (NODE_ID < 0 || NODE_ID > MAX_NODE_ID) {
      throw new IllegalArgumentException(String.format("NodeId must be between %d and %d",
              0, MAX_NODE_ID));
    }
    return encode(generateId());
  }

  public synchronized BigInteger generateId() {
    return BigInteger.valueOf(nextId());
  }

  private static Long getNodeId() {
    long nodeId;
    nodeId = 100L * TENANT_NUMBER.longValue() + CONTEXT_NUMBER.longValue();
    return nodeId;
  }

  private String encode(BigInteger value) {
    List<Character> result = new ArrayList<>();
    BigInteger base = new BigInteger("" + DICTIONARY_62.length);
    int exponent = 1;
    BigInteger remaining = value;
    while (true) {
      BigInteger a = base.pow(exponent);
      BigInteger b = remaining.mod(a);
      BigInteger c = base.pow(exponent - 1);
      BigInteger d = b.divide(c);
      result.add(DICTIONARY_62[d.intValue()]);
      remaining = remaining.subtract(b);
      if (remaining.equals(BigInteger.ZERO)) {
        break;
      }
      exponent++;
    }

    StringBuilder sb = new StringBuilder();
    for (int i = result.size() - 1; i >= 0; i--) {
      sb.append(result.get(i));
    }
    return sb.toString();
  }

  private synchronized long nextId() {
    long currentTimestamp = timestamp();

    if (currentTimestamp < lastTimestamp) {
      throw new IllegalStateException("Invalid System Clock!");
    }

    if (currentTimestamp == lastTimestamp) {
      sequence = (sequence + 1) & MAX_SEQUENCE;
      if (sequence == 0) {
        currentTimestamp = waitNextMillis(currentTimestamp);
      }
    } else {
      sequence = 0;
    }
    lastTimestamp = currentTimestamp;

    return currentTimestamp << (NODE_ID_BITS + SEQUENCE_BITS)
            | (NODE_ID << SEQUENCE_BITS)
            | sequence;
  }

  private long timestamp() {
    return Instant.now().toEpochMilli() - CUSTOM_EPOCH;
  }

  private long waitNextMillis(long currentTimestamp) {
    while (currentTimestamp == lastTimestamp) {
      currentTimestamp = timestamp();
    }
    return currentTimestamp;
  }

}
