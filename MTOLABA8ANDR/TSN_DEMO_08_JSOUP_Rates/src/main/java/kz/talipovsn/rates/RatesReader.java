package kz.talipovsn.rates;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class RatesReader {

    private static final String BASE_URL = "https://www.iqair.com/ru/kazakhstan/pavlodar";
    public static String getRatesData() {
        StringBuilder data = new StringBuilder();
        try {
            Document doc = Jsoup.connect(BASE_URL).timeout(5000).get();
            data.append("Уровень загрязнения:\n");
            Elements e = doc.select("div.table-wrapper");
            Elements tables = e.select("table");
            Element table = tables.get(1);
            int i = 0;

            for (Element row : table.select("tr")) {

                for (Element col : row.select("td:lt(2)")) { //
                    data.append(String.format("%12s ", col.text()));
                    data.append("\n");
                }
                data.append("\n");
            }
        } catch (Exception ignored) {
            return null;
        }
        return data.toString().trim();
    }

}