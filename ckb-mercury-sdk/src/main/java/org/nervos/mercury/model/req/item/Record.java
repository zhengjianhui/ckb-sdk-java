package org.nervos.mercury.model.req.item;

import com.google.common.primitives.Bytes;
import com.google.gson.annotations.SerializedName;
import java.util.Objects;
import org.nervos.ckb.type.OutPoint;
import org.nervos.ckb.type.Script;
import org.nervos.ckb.utils.Numeric;

public class Record implements Item {

  public static final String SCRIPT_TYPE = "0x00";

  public static final String Address_TYPE = "0x01";

  @SerializedName("Record")
  public String record;

  public transient OutPoint outPoint;

  public transient Script script;

  public transient String address;

  public Record() {}

  public Record(OutPoint outPoint, Script script) {
    this(outPoint, script, null);
  }

  public Record(OutPoint outPoint, String address) {
    this(outPoint, null, address);
  }

  public Record(OutPoint outPoint, Script script, String address) {
    this.outPoint = outPoint;
    this.script = script;
    this.address = address;
    this.record = this.toRecordItem();
  }

  public String toRecordItem() {
    byte[] record =
        Bytes.concat(
            Numeric.hexStringToByteArray(this.outPoint.txHash),
            Numeric.hexStringToByteArray(this.outPoint.index));

    if (Objects.nonNull(this.script)) {
      return Numeric.toHexString(
          Bytes.concat(
              record,
              Numeric.hexStringToByteArray(Record.SCRIPT_TYPE),
              Numeric.hexStringToByteArray(this.script.computeHash())));
    } else {
      return Numeric.toHexString(
          Bytes.concat(
              record,
              Numeric.hexStringToByteArray(Record.Address_TYPE),
              Numeric.hexStringToByteArray(this.address)));
    }
  }
}
