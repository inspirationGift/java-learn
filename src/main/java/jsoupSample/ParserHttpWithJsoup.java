package jsoupSample;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.jsoup.Jsoup.parse;

public class ParserHttpWithJsoup {

    private static Document getPage() {
        String url = "http://pogoda.spb.ru/";
        Document page = null;
        try {
            page = parse(new URL(url), 3000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return page;
    }

    //\d{2}\.\d{2}
    private static Pattern pattern = Pattern.compile("\\d{2}\\.\\d{2}");

    private static String getDateFromString(String s) throws Exception {
        Matcher matcher = pattern.matcher(s);

        if (matcher.find()) {
            return matcher.group();
        }
        throw new Exception("Cant extract date");
    }

    private static List printFourValuesInArray(Elements values) {
        List list = new ArrayList<String>();

        for (Element el :
                values) {
            list.add(el.text() + "\n");
        }
        List listNew = new ArrayList();
        String s = "";

        Collections.reverse(list);

        int v = list.size();

        for (int i = 0, m = 1; i < list.size(); i++, m++) {
            s = list.get(i) + s;
            if (m == 4) {
                listNew.add(s);
                s = "";
                m = 0;
            }
        }
        if (!s.equals("")) {
            listNew.add(s);
        }
        Collections.reverse(listNew);
        return listNew;
    }

    public static void main(String[] args) throws Exception {
        // css query language
        Document page = getPage();
        Elements wTable = page.select("table[class=wt]");
        Elements names = wTable.select("tr[class=wth]");
        Elements values = wTable.select("tr[valign=top]");
        List listVal = printFourValuesInArray(values);
//        System.out.println(listVal);

        int index = 0;
        for (Element name : names) {
            String dateString = name.select("th[id=dt]").text();
            String date;
            date = getDateFromString(dateString);
            System.out.println(date + "  Явление  Т  Давл   Влажность  Ветер");
            System.out.println(listVal.get(index));
            index++;
        }
    }
}
