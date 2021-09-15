package org.nervos.mercury.model.req.item;

import org.nervos.ckb.type.OutPoint;
import org.nervos.ckb.type.Script;

public interface Item {
  static Item newAddressItem(String address) {
    return new Address(address);
  }

  static Item newIdentityItemByCkb(String pubKey) {
    return new Identity(Identity.IDENTITY_FLAGS_CKB, pubKey);
  }

  static Item newRecordItemByScript(OutPoint point, Script script) {
    return new Record(point, script);
  }

  static Item newRecordItemByAddress(OutPoint point, String address) {
    return new Record(point, address);
  }
}
