package net.zelenaya.sorm.service;

import net.zelenaya.sorm.domain.billing.UserBil;
import net.zelenaya.sorm.util.Util;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Component
public class UserBilService {

    public List<String> getAbonentFile(List<UserBil> userBils){
        List<String> stringList = new ArrayList<>();
        String header = "ID;REGION_ID;CONTRACT_DATE;CONTRACT;ACCOUNT;ACTUAL_FROM;ACTUAL_TO;" +
                "ABONENT_TYPE;NAME_INFO_TYPE;UNSTRUCT_NAME;BIRTH_DATE;IDENT_CARD_TYPE_ID;" +
                "IDENT_CARD_TYPE;IDENT_CARD_UNSTRUCT;FULL_NAME;INN;STATUS;NETWORK_TYPE;" +
                "INTERNAL_ID1;INTERNAL_ID2";
        stringList.add(header);

        for (UserBil user:userBils) {
            StringJoiner stj = new StringJoiner(";");
            stj.add(user.getID().toString());
            stj.add(user.getREGION_ID().toString());
            stj.add(Util.getStringDate(user.getCONTRACT_DATE()));
            stj.add(user.getCONTRACT());
            stj.add(user.getACCOUNT());
            stj.add(Util.getStringDate(user.getACTUAL_FROM()));
            stj.add(Util.getStringDate(user.getACTUAL_TO()));
            stj.add(user.getABONENT_TYPE().toString());
            stj.add(user.getNAME_INFO_TYPE().toString());
            stj.add(user.getUNSTRUCT_NAME());
            stj.add(user.getBIRTH_DATE());
            stj.add(user.getIDENT_CARD_TYPE_ID().toString());
            stj.add(user.getIDENT_CARD_TYPE().toString());
            stj.add(user.getIDENT_CARD_UNSTRUCT());
            stj.add(user.getFULL_NAME());
            stj.add(user.getINN());
            stj.add(user.getSTATUS().toString());
            stj.add(user.getNETWORK_TYPE().toString());
            stj.add(user.getINTERNAL_ID1().toString());
            stj.add(user.getINTERNAL_ID2().toString());
            stringList.add(stj.toString());
        }
        return stringList;
    }
}
