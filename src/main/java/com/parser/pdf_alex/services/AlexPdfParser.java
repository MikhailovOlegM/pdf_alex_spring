package com.parser.pdf_alex.services;

import com.google.gson.Gson;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.LocationTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.parser.pdf_alex.pages.DataContainer;
import com.parser.pdf_alex.pages.FirstPageContainer;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Optional;
import java.util.regex.Matcher;

public class AlexPdfParser {


    public FirstPageContainer parse(String path) throws IOException, ParseException {
//        String path = "/media/hdd/Documents/coding/pdf_alex/src/main/resources/receipt.pdf";
        File file = new File(path);

        if (!file.exists()) {
            return null;
        }

        PdfReader reader = new PdfReader(path);
        FirstPageContainer fp = new FirstPageContainer();

        // не забываем, что нумерация страниц в PDF начинается с единицы.
        for (int i = 1; i <= reader.getNumberOfPages(); ++i) {

            TextExtractionStrategy strategy = new LocationTextExtractionStrategy();
            String text = PdfTextExtractor.getTextFromPage(reader, i, strategy);
            String[] textArray = text.split("\n");

            if (i == 1) {

                for (int pageIndex = 0; pageIndex < textArray.length; pageIndex++) {
                    if (pageIndex == 1) {
                        fp.setCityLab(textArray[pageIndex].split(" ")[1]);
                    }
                    String tmp = textArray[pageIndex];


                    if (tmp.contains("Найвища виміряна концентрація")) {
                        break;
                    }

                    if (tmp.contains("Пацієнт")) {
                        fp.setUserName(tmp.substring(tmp.indexOf(": ") + 1));
                    } else if (tmp.contains("Дата народження")) {
                        fp.setDob(FirstPageContainer.DateFor.parse(tmp.substring(tmp.indexOf(": ") + 1)));
                    } else if (tmp.contains("Штрих код:")) {
                        fp.setBarcode(tmp.substring(tmp.indexOf(": ") + 1));
                    } else if (tmp.contains("Забір крові")) {
                        fp.setBloodSampleDate(FirstPageContainer.DateFor.parse(tmp.substring(tmp.indexOf(": ") + 1)));
                    } else if (tmp.contains("Протестовано")) {
                        fp.setTestDate(FirstPageContainer.DateFor.parse(tmp.substring(tmp.indexOf(": ") + 1)));
                    } else if (tmp.contains("Надрукований на")) {
                        fp.setReleasedDate(FirstPageContainer.DateFor.parse(tmp.substring(tmp.indexOf(": ") + 1)));
                    }

                    if (pageIndex > 12) {
//                        System.out.println("- PRE: " + tmp);

                        if (tmp.contains("детермінанти)")) {
                            tmp = "CCD (перехресно-реактивні вуглеводні " + tmp;
                        }
                        Matcher matcher = FirstPageContainer.TableStrim.matcher(tmp);
                        Matcher number = FirstPageContainer.Number.matcher(tmp);

                        if (number.find()) {
                            int index = number.start() + 3;
                            if (index >= tmp.length()) {
                                String name = tmp.split("([0-9]{1})")[0].trim();
                                int rate = Integer.parseInt(tmp.substring(tmp.split("([0-9]{1})")[0].length(), tmp.length()));

                                fp.addValue(name, rate);

                            } else {


                                String res1 = tmp.substring(0, index);
                                String res2 = tmp.substring(index);

                                String name = res1.split("([0-9]{1})")[0].trim();
                                int rate = Integer.parseInt(res1.substring(res1.split("([0-9]{1})")[0].length()).trim());

                                String name2 = res2.split("([0-9]{1}$)")[0].trim();
                                int rate2 = Integer.parseInt(res2.substring(res2.split("([0-9]{1})$")[0].length()).trim());

                                fp.addValue(name, rate);
                                fp.addValue(name2, rate2);
                            }
                        }
                    }

                }
            } else {
                for (int pageIndex = 0; pageIndex < textArray.length; pageIndex++) {
                    String tmp = textArray[pageIndex];

                    Matcher dataParser = FirstPageContainer.OtherData.matcher(tmp);
                    if (dataParser.find()) {
                        String name = Optional.ofNullable(dataParser.group(1)).orElse("").trim();
                        String allergen = Optional.ofNullable(dataParser.group(2)).orElse("").trim();
                        String type = Optional.ofNullable(dataParser.group(4)).orElse("").trim();
                        String proteins = Optional.ofNullable(dataParser.group(5)).orElse("").trim();
                        String value = Optional.ofNullable(dataParser.group(6)).orElse("").replace("≤", "").trim();

                        DataContainer con = new DataContainer(name, allergen, type, proteins, value);
                        fp.addValue(con);
                    }

                }

            }

        }
//        System.out.println(new Gson().toJson(fp));
        return fp;

    }
}
