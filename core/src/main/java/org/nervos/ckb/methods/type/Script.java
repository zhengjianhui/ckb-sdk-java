package org.nervos.ckb.methods.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.nervos.ckb.crypto.Hash;
import org.nervos.ckb.utils.Numeric;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Created by duanyytop on 2019-01-08.
 * Copyright © 2018 Nervos Foundation. All rights reserved.
 */
public class Script {

    public int version;
    public String binary;
    public String reference;
    @JsonProperty("signed_args")
    public List<String> signedArgs;
    public List<String> args;

    public Script(){}

    public Script(int version, String binary, String reference, List<String> signedArgs, List<String> args) {
        this.version = version;
        this.binary = binary;
        this.reference = reference;
        this.signedArgs = signedArgs;
        this.args = args;
    }

    public Script(int version, String reference, List<String> signedArgs, List<String> args) {
        this(version, null, reference, signedArgs, args);
    }

    public String getTypeHash() {
        if (reference != null) {
            Hash.update(Numeric.hexStringToByteArray(reference));
        }
        Hash.update("|".getBytes(StandardCharsets.UTF_8));
        if (binary != null) {
            Hash.update(Numeric.hexStringToByteArray(binary));
        }
        for (String arg: signedArgs) {
            Hash.update(Numeric.hexStringToByteArray(arg));
        }
        return Hash.doFinalString();
    }

}
