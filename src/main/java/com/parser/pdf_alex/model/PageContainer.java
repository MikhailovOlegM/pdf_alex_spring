package com.parser.pdf_alex.model;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class PageContainer {
    private String cityLab;
    private String userName;
    private Date dob;
    private String barcode;
    private Date bloodSampleDate;
    private Date testDate;
    private Date releasedDate;

    public static final SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
    public static final Pattern TableStrim = Pattern.compile("^([A-Za-zА-Яа-яёЁЇїІіЄєҐґ' ]+ [0-9]{1}) ([A-Za-zА-Яа-яёЁЇїІіЄєҐґ\\-' ]+([0-9]| ){1,})$", Pattern.MULTILINE);
    public static final Pattern Number = Pattern.compile("( [0-9] )|( [0-9])");

    public static final Pattern OtherData = Pattern.compile("(^[А-Яа-яa-zA-ZёЁЇїІіЄєҐґ() ]+) ([A-Za-z_ ]+( [0-9]*[.,]?[0-9]+)?) ([А-Я]{1})( [A-Za-zА-Яа-яёЁЇїІіЄєҐґβα/,\\- /0-9]+)? ((≤ )?[0-9]*[,]?[0-9]+$)");

    private Map<String, Integer> shortDataContainer = new HashMap<>();
    private List<DataContainer> dataList = new ArrayList<>();

    public void addValue(String name, int rate) {
        shortDataContainer.put(name, rate);
    }

    public void addValue(DataContainer container) {
        this.dataList.add(container);
    }

    public String getCityLab() {
        return cityLab;
    }

    public void setCityLab(String cityLab) {
        this.cityLab = cityLab;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Date getBloodSampleDate() {
        return bloodSampleDate;
    }

    public void setBloodSampleDate(Date bloodSampleDate) {
        this.bloodSampleDate = bloodSampleDate;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public Date getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(Date releasedDate) {
        this.releasedDate = releasedDate;
    }

}
