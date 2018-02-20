import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;

/*
 *
 * @author Vladislav Klochkov
 * @create 20.02.2018
 *
 */
public class TestQuestCSSSR {
    public static void main(String[] args) {
        // Задаем строку
        String s = "сапог сарай арбуз болт бокс биржа";

        // Разбиваем на слова
        String[] list = s.split(" ");

        // Задаем итоговую маппу, используя ConcurrentSkipListMap чтобы избежать ConcurrentModificationException
        Map<String, List<String>> totalMap = new ConcurrentSkipListMap<>();

        // Запускаем цикл пом ассиву
        for (int i = 0; i < list.length; i++) {

            // Проверяем на наличия ключа по первой буквы в итоговой маппе
            if (totalMap.containsKey(String.valueOf(list[i].charAt(0)))) {

                // Если есть такой ключ, то добавляем слово в значение
                totalMap.get(String.valueOf(list[i].charAt(0))).add(list[i]);
            } else {

                // Если нет, добавляем в маппу ключ и значение
                List<String> temp = new ArrayList();
                temp.add(list[i]);
                totalMap.put(String.valueOf(list[i].charAt(0)), temp);
            }
        }

        // Удаляем ключи и значения, если меньше 2х слов, далее сортируем значение
        for (Map.Entry<String, List<String>> map : totalMap.entrySet()) {
            if (map.getValue().size() < 2) {
                totalMap.remove(map.getKey());
            }
            Collections.sort(map.getValue(), new Comparator<String>() {
                public int compare(String o1, String o2) {
                    return o1.toString().compareTo(o2.toString());
                }
            });
        }
        System.out.println(totalMap);
    }
}
