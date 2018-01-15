
package com.example.stas.rustal;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModel implements Parcelable
{

    @SerializedName("UF_USER_WORK_STATUS")
    @Expose
    private String uFUSERWORKSTATUS;
    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("NAME")
    @Expose
    private String nAME;
    @SerializedName("LAST_NAME")
    @Expose
    private String lASTNAME;
    @SerializedName("EMAIL")
    @Expose
    private String eMAIL;
    @SerializedName("PERSONAL_PROFESSION")
    @Expose
    private Object pERSONALPROFESSION;
    @SerializedName("PERSONAL_WWW")
    @Expose
    private Object pERSONALWWW;
    @SerializedName("PERSONAL_ICQ")
    @Expose
    private Object pERSONALICQ;
    @SerializedName("PERSONAL_GENDER")
    @Expose
    private String pERSONALGENDER;
    @SerializedName("PERSONAL_BIRTHDATE")
    @Expose
    private Object pERSONALBIRTHDATE;
    @SerializedName("PERSONAL_PHOTO")
    @Expose
    private Object pERSONALPHOTO;
    @SerializedName("PERSONAL_PHONE")
    @Expose
    private Object pERSONALPHONE;
    @SerializedName("PERSONAL_FAX")
    @Expose
    private Object pERSONALFAX;
    @SerializedName("PERSONAL_MOBILE")
    @Expose
    private Object pERSONALMOBILE;
    @SerializedName("PERSONAL_PAGER")
    @Expose
    private Object pERSONALPAGER;
    @SerializedName("PERSONAL_STREET")
    @Expose
    private Object pERSONALSTREET;
    @SerializedName("PERSONAL_MAILBOX")
    @Expose
    private Object pERSONALMAILBOX;
    @SerializedName("PERSONAL_CITY")
    @Expose
    private Object pERSONALCITY;
    @SerializedName("PERSONAL_STATE")
    @Expose
    private Object pERSONALSTATE;
    @SerializedName("PERSONAL_ZIP")
    @Expose
    private Object pERSONALZIP;
    @SerializedName("PERSONAL_COUNTRY")
    @Expose
    private Object pERSONALCOUNTRY;
    @SerializedName("PERSONAL_NOTES")
    @Expose
    private Object pERSONALNOTES;
    @SerializedName("WORK_COMPANY")
    @Expose
    private Object wORKCOMPANY;
    @SerializedName("WORK_DEPARTMENT")
    @Expose
    private Object wORKDEPARTMENT;
    @SerializedName("WORK_POSITION")
    @Expose
    private Object wORKPOSITION;
    @SerializedName("WORK_WWW")
    @Expose
    private Object wORKWWW;
    @SerializedName("WORK_PHONE")
    @Expose
    private Object wORKPHONE;
    @SerializedName("WORK_FAX")
    @Expose
    private Object wORKFAX;
    @SerializedName("WORK_PAGER")
    @Expose
    private Object wORKPAGER;
    @SerializedName("WORK_STREET")
    @Expose
    private Object wORKSTREET;
    @SerializedName("WORK_MAILBOX")
    @Expose
    private Object wORKMAILBOX;
    @SerializedName("WORK_CITY")
    @Expose
    private Object wORKCITY;
    @SerializedName("WORK_STATE")
    @Expose
    private Object wORKSTATE;
    @SerializedName("WORK_ZIP")
    @Expose
    private Object wORKZIP;
    @SerializedName("WORK_COUNTRY")
    @Expose
    private Object wORKCOUNTRY;
    @SerializedName("WORK_PROFILE")
    @Expose
    private Object wORKPROFILE;
    @SerializedName("WORK_LOGO")
    @Expose
    private Object wORKLOGO;
    @SerializedName("WORK_NOTES")
    @Expose
    private Object wORKNOTES;
    @SerializedName("UF_USER_TOKEN")
    @Expose
    private Object uFUSERTOKEN;
    @SerializedName("USER_POSITION")
    @Expose
    private Object uSERPOSITION;
    @SerializedName("IS_LOGIN")
    @Expose
    private Boolean iSLOGIN;
    public final static Parcelable.Creator<LoginModel> CREATOR = new Creator<LoginModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public LoginModel createFromParcel(Parcel in) {
            return new LoginModel(in);
        }

        public LoginModel[] newArray(int size) {
            return (new LoginModel[size]);
        }

    }
            ;

    protected LoginModel(Parcel in) {
        this.uFUSERWORKSTATUS = ((String) in.readValue((String.class.getClassLoader())));
        this.iD = ((String) in.readValue((String.class.getClassLoader())));
        this.nAME = ((String) in.readValue((String.class.getClassLoader())));
        this.lASTNAME = ((String) in.readValue((String.class.getClassLoader())));
        this.eMAIL = ((String) in.readValue((String.class.getClassLoader())));
        this.pERSONALPROFESSION = in.readValue((Object.class.getClassLoader()));
        this.pERSONALWWW = in.readValue((Object.class.getClassLoader()));
        this.pERSONALICQ = in.readValue((Object.class.getClassLoader()));
        this.pERSONALGENDER = ((String) in.readValue((String.class.getClassLoader())));
        this.pERSONALBIRTHDATE = in.readValue((Object.class.getClassLoader()));
        this.pERSONALPHOTO = in.readValue((Object.class.getClassLoader()));
        this.pERSONALPHONE = in.readValue((Object.class.getClassLoader()));
        this.pERSONALFAX = in.readValue((Object.class.getClassLoader()));
        this.pERSONALMOBILE = in.readValue((Object.class.getClassLoader()));
        this.pERSONALPAGER = in.readValue((Object.class.getClassLoader()));
        this.pERSONALSTREET = in.readValue((Object.class.getClassLoader()));
        this.pERSONALMAILBOX = in.readValue((Object.class.getClassLoader()));
        this.pERSONALCITY = in.readValue((Object.class.getClassLoader()));
        this.pERSONALSTATE = in.readValue((Object.class.getClassLoader()));
        this.pERSONALZIP = in.readValue((Object.class.getClassLoader()));
        this.pERSONALCOUNTRY = in.readValue((Object.class.getClassLoader()));
        this.pERSONALNOTES = in.readValue((Object.class.getClassLoader()));
        this.wORKCOMPANY = in.readValue((Object.class.getClassLoader()));
        this.wORKDEPARTMENT = in.readValue((Object.class.getClassLoader()));
        this.wORKPOSITION = in.readValue((Object.class.getClassLoader()));
        this.wORKWWW = in.readValue((Object.class.getClassLoader()));
        this.wORKPHONE = in.readValue((Object.class.getClassLoader()));
        this.wORKFAX = in.readValue((Object.class.getClassLoader()));
        this.wORKPAGER = in.readValue((Object.class.getClassLoader()));
        this.wORKSTREET = in.readValue((Object.class.getClassLoader()));
        this.wORKMAILBOX = in.readValue((Object.class.getClassLoader()));
        this.wORKCITY = in.readValue((Object.class.getClassLoader()));
        this.wORKSTATE = in.readValue((Object.class.getClassLoader()));
        this.wORKZIP = in.readValue((Object.class.getClassLoader()));
        this.wORKCOUNTRY = in.readValue((Object.class.getClassLoader()));
        this.wORKPROFILE = in.readValue((Object.class.getClassLoader()));
        this.wORKLOGO = in.readValue((Object.class.getClassLoader()));
        this.wORKNOTES = in.readValue((Object.class.getClassLoader()));
        this.uFUSERTOKEN = in.readValue((Object.class.getClassLoader()));
        this.uSERPOSITION = in.readValue((Boolean.class.getClassLoader()));
        this.iSLOGIN = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
    }

    public LoginModel() {
    }

    public String getUFUSERWORKSTATUS() {
        return uFUSERWORKSTATUS;
    }

    public void setUFUSERWORKSTATUS(String uFUSERWORKSTATUS) {
        this.uFUSERWORKSTATUS = uFUSERWORKSTATUS;
    }

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getNAME() {
        return nAME;
    }

    public void setNAME(String nAME) {
        this.nAME = nAME;
    }

    public String getLASTNAME() {
        return lASTNAME;
    }

    public void setLASTNAME(String lASTNAME) {
        this.lASTNAME = lASTNAME;
    }

    public String getEMAIL() {
        return eMAIL;
    }

    public void setEMAIL(String eMAIL) {
        this.eMAIL = eMAIL;
    }

    public Object getPERSONALPROFESSION() {
        return pERSONALPROFESSION;
    }

    public void setPERSONALPROFESSION(Object pERSONALPROFESSION) {
        this.pERSONALPROFESSION = pERSONALPROFESSION;
    }

    public Object getPERSONALWWW() {
        return pERSONALWWW;
    }

    public void setPERSONALWWW(Object pERSONALWWW) {
        this.pERSONALWWW = pERSONALWWW;
    }

    public Object getPERSONALICQ() {
        return pERSONALICQ;
    }

    public void setPERSONALICQ(Object pERSONALICQ) {
        this.pERSONALICQ = pERSONALICQ;
    }

    public String getPERSONALGENDER() {
        return pERSONALGENDER;
    }

    public void setPERSONALGENDER(String pERSONALGENDER) {
        this.pERSONALGENDER = pERSONALGENDER;
    }

    public Object getPERSONALBIRTHDATE() {
        return pERSONALBIRTHDATE;
    }

    public void setPERSONALBIRTHDATE(Object pERSONALBIRTHDATE) {
        this.pERSONALBIRTHDATE = pERSONALBIRTHDATE;
    }

    public Object getPERSONALPHOTO() {
        return pERSONALPHOTO;
    }

    public void setPERSONALPHOTO(Object pERSONALPHOTO) {
        this.pERSONALPHOTO = pERSONALPHOTO;
    }

    public Object getPERSONALPHONE() {
        return pERSONALPHONE;
    }

    public void setPERSONALPHONE(Object pERSONALPHONE) {
        this.pERSONALPHONE = pERSONALPHONE;
    }

    public Object getPERSONALFAX() {
        return pERSONALFAX;
    }

    public void setPERSONALFAX(Object pERSONALFAX) {
        this.pERSONALFAX = pERSONALFAX;
    }

    public Object getPERSONALMOBILE() {
        return pERSONALMOBILE;
    }

    public void setPERSONALMOBILE(Object pERSONALMOBILE) {
        this.pERSONALMOBILE = pERSONALMOBILE;
    }

    public Object getPERSONALPAGER() {
        return pERSONALPAGER;
    }

    public void setPERSONALPAGER(Object pERSONALPAGER) {
        this.pERSONALPAGER = pERSONALPAGER;
    }

    public Object getPERSONALSTREET() {
        return pERSONALSTREET;
    }

    public void setPERSONALSTREET(Object pERSONALSTREET) {
        this.pERSONALSTREET = pERSONALSTREET;
    }

    public Object getPERSONALMAILBOX() {
        return pERSONALMAILBOX;
    }

    public void setPERSONALMAILBOX(Object pERSONALMAILBOX) {
        this.pERSONALMAILBOX = pERSONALMAILBOX;
    }

    public Object getPERSONALCITY() {
        return pERSONALCITY;
    }

    public void setPERSONALCITY(Object pERSONALCITY) {
        this.pERSONALCITY = pERSONALCITY;
    }

    public Object getPERSONALSTATE() {
        return pERSONALSTATE;
    }

    public void setPERSONALSTATE(Object pERSONALSTATE) {
        this.pERSONALSTATE = pERSONALSTATE;
    }

    public Object getPERSONALZIP() {
        return pERSONALZIP;
    }

    public void setPERSONALZIP(Object pERSONALZIP) {
        this.pERSONALZIP = pERSONALZIP;
    }

    public Object getPERSONALCOUNTRY() {
        return pERSONALCOUNTRY;
    }

    public void setPERSONALCOUNTRY(Object pERSONALCOUNTRY) {
        this.pERSONALCOUNTRY = pERSONALCOUNTRY;
    }

    public Object getPERSONALNOTES() {
        return pERSONALNOTES;
    }

    public void setPERSONALNOTES(Object pERSONALNOTES) {
        this.pERSONALNOTES = pERSONALNOTES;
    }

    public Object getWORKCOMPANY() {
        return wORKCOMPANY;
    }

    public void setWORKCOMPANY(Object wORKCOMPANY) {
        this.wORKCOMPANY = wORKCOMPANY;
    }

    public Object getWORKDEPARTMENT() {
        return wORKDEPARTMENT;
    }

    public void setWORKDEPARTMENT(Object wORKDEPARTMENT) {
        this.wORKDEPARTMENT = wORKDEPARTMENT;
    }

    public Object getWORKPOSITION() {
        return wORKPOSITION;
    }

    public void setWORKPOSITION(Object wORKPOSITION) {
        this.wORKPOSITION = wORKPOSITION;
    }

    public Object getWORKWWW() {
        return wORKWWW;
    }

    public void setWORKWWW(Object wORKWWW) {
        this.wORKWWW = wORKWWW;
    }

    public Object getWORKPHONE() {
        return wORKPHONE;
    }

    public void setWORKPHONE(Object wORKPHONE) {
        this.wORKPHONE = wORKPHONE;
    }

    public Object getWORKFAX() {
        return wORKFAX;
    }

    public void setWORKFAX(Object wORKFAX) {
        this.wORKFAX = wORKFAX;
    }

    public Object getWORKPAGER() {
        return wORKPAGER;
    }

    public void setWORKPAGER(Object wORKPAGER) {
        this.wORKPAGER = wORKPAGER;
    }

    public Object getWORKSTREET() {
        return wORKSTREET;
    }

    public void setWORKSTREET(Object wORKSTREET) {
        this.wORKSTREET = wORKSTREET;
    }

    public Object getWORKMAILBOX() {
        return wORKMAILBOX;
    }

    public void setWORKMAILBOX(Object wORKMAILBOX) {
        this.wORKMAILBOX = wORKMAILBOX;
    }

    public Object getWORKCITY() {
        return wORKCITY;
    }

    public void setWORKCITY(Object wORKCITY) {
        this.wORKCITY = wORKCITY;
    }

    public Object getWORKSTATE() {
        return wORKSTATE;
    }

    public void setWORKSTATE(Object wORKSTATE) {
        this.wORKSTATE = wORKSTATE;
    }

    public Object getWORKZIP() {
        return wORKZIP;
    }

    public void setWORKZIP(Object wORKZIP) {
        this.wORKZIP = wORKZIP;
    }

    public Object getWORKCOUNTRY() {
        return wORKCOUNTRY;
    }

    public void setWORKCOUNTRY(Object wORKCOUNTRY) {
        this.wORKCOUNTRY = wORKCOUNTRY;
    }

    public Object getWORKPROFILE() {
        return wORKPROFILE;
    }

    public void setWORKPROFILE(Object wORKPROFILE) {
        this.wORKPROFILE = wORKPROFILE;
    }

    public Object getWORKLOGO() {
        return wORKLOGO;
    }

    public void setWORKLOGO(Object wORKLOGO) {
        this.wORKLOGO = wORKLOGO;
    }

    public Object getWORKNOTES() {
        return wORKNOTES;
    }

    public void setWORKNOTES(Object wORKNOTES) {
        this.wORKNOTES = wORKNOTES;
    }

    public Object getUFUSERTOKEN() {
        return uFUSERTOKEN;
    }

    public void setUFUSERTOKEN(Object uFUSERTOKEN) {
        this.uFUSERTOKEN = uFUSERTOKEN;
    }

    public Object getUSERPOSITION() {
        return uSERPOSITION;
    }

    public void setUSERPOSITION(Boolean uSERPOSITION) {
        this.uSERPOSITION = uSERPOSITION;
    }

    public Boolean getISLOGIN() {
        return iSLOGIN;
    }

    public void setISLOGIN(Boolean iSLOGIN) {
        this.iSLOGIN = iSLOGIN;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(uFUSERWORKSTATUS);
        dest.writeValue(iD);
        dest.writeValue(nAME);
        dest.writeValue(lASTNAME);
        dest.writeValue(eMAIL);
        dest.writeValue(pERSONALPROFESSION);
        dest.writeValue(pERSONALWWW);
        dest.writeValue(pERSONALICQ);
        dest.writeValue(pERSONALGENDER);
        dest.writeValue(pERSONALBIRTHDATE);
        dest.writeValue(pERSONALPHOTO);
        dest.writeValue(pERSONALPHONE);
        dest.writeValue(pERSONALFAX);
        dest.writeValue(pERSONALMOBILE);
        dest.writeValue(pERSONALPAGER);
        dest.writeValue(pERSONALSTREET);
        dest.writeValue(pERSONALMAILBOX);
        dest.writeValue(pERSONALCITY);
        dest.writeValue(pERSONALSTATE);
        dest.writeValue(pERSONALZIP);
        dest.writeValue(pERSONALCOUNTRY);
        dest.writeValue(pERSONALNOTES);
        dest.writeValue(wORKCOMPANY);
        dest.writeValue(wORKDEPARTMENT);
        dest.writeValue(wORKPOSITION);
        dest.writeValue(wORKWWW);
        dest.writeValue(wORKPHONE);
        dest.writeValue(wORKFAX);
        dest.writeValue(wORKPAGER);
        dest.writeValue(wORKSTREET);
        dest.writeValue(wORKMAILBOX);
        dest.writeValue(wORKCITY);
        dest.writeValue(wORKSTATE);
        dest.writeValue(wORKZIP);
        dest.writeValue(wORKCOUNTRY);
        dest.writeValue(wORKPROFILE);
        dest.writeValue(wORKLOGO);
        dest.writeValue(wORKNOTES);
        dest.writeValue(uFUSERTOKEN);
        dest.writeValue(uSERPOSITION);
        dest.writeValue(iSLOGIN);
    }

    public int describeContents() {
        return 0;
    }

}