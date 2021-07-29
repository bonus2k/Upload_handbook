package net.zelenaya.sorm.domain.billing;

import lombok.Data;
import org.springframework.data.annotation.Immutable;

import javax.persistence.*;


@Entity
@Data
@Immutable
@Table(name = "public.users")
public class UserBil {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    @Transient
    private Integer REGION_ID; //из собственного справочника

    public Integer getREGION_ID() {
        return 1; // TODO: нужен справочник
    }

    @Column(name = "create_date")
    private Long CONTRACT_DATE;

    public Long getCONTRACT_DATE() {
        return CONTRACT_DATE * 1000;
    }

    @Column(name = "basic_account")
    private String CONTRACT;

    // TODO: префикс пока заходкоржен
    public String getCONTRACT() {
        return "45690"+CONTRACT;
    }

    @Column(name = "basic_account", insertable = false, updatable = false)
    private String ACCOUNT;

    @Column(name = "create_date", insertable = false, updatable = false)
    private Long ACTUAL_FROM;

    public Long getACTUAL_FROM() {
        return ACTUAL_FROM*1000;
    }

    @Transient
    private Long ACTUAL_TO;

    public Long getACTUAL_TO() {
        return 2524597199345L; //31.12.2049 23:59:59
    }

    @Column(name = "is_juridical")
    private Integer ABONENT_TYPE;

    public Integer getABONENT_TYPE() {
        return ABONENT_TYPE+42;
    }

    @Transient
    private Integer NAME_INFO_TYPE;

    public Integer getNAME_INFO_TYPE() {
        return 1;
    }

    @Column(name = "full_name")
    private String UNSTRUCT_NAME;

    @Transient
    private String BIRTH_DATE;

    @Transient
    private Integer IDENT_CARD_TYPE_ID;

    public Integer getIDENT_CARD_TYPE_ID() {
        return 1; // TODO: нужен справочник
    }

    @Transient
    private Integer IDENT_CARD_TYPE;

    public Integer getIDENT_CARD_TYPE() {
        return 1;
    }

    @Column(name = "passport")
    private String IDENT_CARD_UNSTRUCT;

    @Column(name = "full_name", insertable = false, updatable = false)
    private String FULL_NAME;

    public String getFULL_NAME() {
        if (ABONENT_TYPE == 1) {
            return FULL_NAME;
        } else return "";
    }

    @Column(name = "tax_number")
    private String INN;

    public String getINN() {
        if (ABONENT_TYPE == 1) {
            return INN;
        } else return "";
    }

    @Transient
    private Integer STATUS;
    // TODO: определить поле в таблице
    public Integer getSTATUS() {
        return 1;
    }

    @Transient
    private Integer NETWORK_TYPE;

    public Integer getNETWORK_TYPE() {
        return 4;
    }

    @Column(name = "id", insertable = false, updatable = false)
    private Integer INTERNAL_ID1;

    @Column(name = "id", insertable = false, updatable = false)
    private Integer INTERNAL_ID2;

    @Override
    public String toString() {
        return ID + ";"
                + REGION_ID + ";"
                + CONTRACT_DATE + ";"
                + CONTRACT + ";"
                + ACCOUNT + ";"
                + ACTUAL_FROM + ";"
                + ACTUAL_TO + ";"
                + ABONENT_TYPE + ";"
                + NAME_INFO_TYPE + ";"
                + UNSTRUCT_NAME + ";"
                + BIRTH_DATE + ";"
                + IDENT_CARD_TYPE_ID + ";"
                + IDENT_CARD_TYPE + ";"
                + IDENT_CARD_UNSTRUCT + ";"
                + FULL_NAME + ";"
                + INN + ";"
                + NETWORK_TYPE + ";"
                + INTERNAL_ID1 + ";"
                + INTERNAL_ID2 + "\n";
    }
}
