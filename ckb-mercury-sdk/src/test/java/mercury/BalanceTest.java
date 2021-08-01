package mercury;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import mercury.constant.AddressWithKeyHolder;
import mercury.constant.MercuryApiFactory;
import mercury.constant.UdtHolder;
import org.junit.jupiter.api.Test;
import org.nervos.ckb.utils.address.AddressTools;
import org.nervos.mercury.model.GetBalancePayloadBuilder;
import org.nervos.mercury.model.req.KeyAddress;
import org.nervos.mercury.model.req.NormalAddress;
import org.nervos.mercury.model.req.QueryAddress;
import org.nervos.mercury.model.resp.GetBalanceResponse;

public class BalanceTest {

  Gson g =
      new GsonBuilder()
          .registerTypeAdapter(QueryAddress.class, new KeyAddress(""))
          .registerTypeAdapter(QueryAddress.class, new NormalAddress(""))
          .create();

  @Test
  void getBalance() {
    try {

      GetBalancePayloadBuilder builder = new GetBalancePayloadBuilder();
      builder.address(new KeyAddress(AddressWithKeyHolder.testAddress4()));

      System.out.println(g.toJson(builder.build()));

      GetBalanceResponse balance = MercuryApiFactory.getApi().getBalance(builder.build());
      assertNotNull(balance, "Balance is not empty");
      System.out.println(g.toJson(balance));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  void getSudtBalance() {

    GetBalancePayloadBuilder builder = new GetBalancePayloadBuilder();
    builder.address(new KeyAddress(AddressWithKeyHolder.testAddress4()));
    builder.addUdtHash(UdtHolder.UDT_HASH);

    System.out.println(g.toJson(builder.build()));

    try {
      GetBalanceResponse balance = MercuryApiFactory.getApi().getBalance(builder.build());
      assertNotNull(balance, "Balance is not empty");
      System.out.println(g.toJson(balance));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  void getAllBalance() {

    GetBalancePayloadBuilder builder = new GetBalancePayloadBuilder();
    builder.address(new KeyAddress(AddressWithKeyHolder.testAddress4()));
    builder.allBalance();

    System.out.println(g.toJson(builder.build()));

    try {
      GetBalanceResponse balance = MercuryApiFactory.getApi().getBalance(builder.build());
      assertNotNull(balance, "Balance is not empty");
      System.out.println(g.toJson(balance));
      System.out.println(balance.balances.size());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  void testNormalAddressWithAcpAddress() {
    GetBalancePayloadBuilder builder = new GetBalancePayloadBuilder();
    builder.address(
        new NormalAddress(AddressTools.generateAcpAddress(AddressWithKeyHolder.testAddress4())));
    builder.addUdtHash(UdtHolder.UDT_HASH);

    System.out.println(g.toJson(builder.build()));

    try {
      GetBalanceResponse balance = MercuryApiFactory.getApi().getBalance(builder.build());
      assertNotNull(balance, "Balance is not empty");
      System.out.println(g.toJson(balance));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  void testNormalAddressWithSecp256k1Address() {
    GetBalancePayloadBuilder builder = new GetBalancePayloadBuilder();
    builder.address(new NormalAddress(AddressWithKeyHolder.testAddress4()));
    builder.addUdtHash(UdtHolder.UDT_HASH);

    System.out.println(g.toJson(builder.build()));

    try {
      GetBalanceResponse balance = MercuryApiFactory.getApi().getBalance(builder.build());
      assertNotNull(balance, "Balance is not empty");
      System.out.println(g.toJson(balance));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}