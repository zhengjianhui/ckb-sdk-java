package org.nervos.ckb.utils;

import java.math.BigInteger;

/** Copyright © 2019 Nervos Foundation. All rights reserved. */
public class EpochParser {

  public static EpochParams parse(String hexEpoch) {
    long epochLong = Numeric.toBigInt(hexEpoch).longValue();
    long length = (epochLong >> 40) & 0xFFFF;
    long index = (epochLong >> 24) & 0xFFFF;
    long number = epochLong & 0xFFFFFF;
    return new EpochParams(length, index, number);
  }

  public static String parse(long length, long index, long number) {
    BigInteger epoch = BigInteger.valueOf((length << 40) + (index << 24) + number);
    return Numeric.toHexStringWithPrefix(epoch);
  }

  public static String parseSince(long length, long index, long number) {
    BigInteger bigInteger = Numeric.toBigInt("0x20").shiftLeft(56);
    BigInteger epoch = BigInteger.valueOf((length << 40) + (index << 24) + number);
    return Numeric.toHexStringWithPrefix(bigInteger.add(epoch));
  }

  public static class EpochParams {
    public long length;
    public long index;
    public long number;

    public EpochParams(long length, long index, long number) {
      this.length = length;
      this.index = index;
      this.number = number;
    }
  }
}
