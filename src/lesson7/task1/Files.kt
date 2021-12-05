@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson7.task1


import java.io.File
import kotlin.math.max


// Урок 7: работа с файлами
// Урок интегральный, поэтому его задачи имеют сильно увеличенную стоимость
// Максимальное количество баллов = 55
// Рекомендуемое количество баллов = 20
// Вместе с предыдущими уроками (пять лучших, 3-7) = 55/103

/**
 * Пример
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Вывести его в выходной файл с именем outputName, выровняв по левому краю,
 * чтобы длина каждой строки не превосходила lineLength.
 * Слова в слишком длинных строках следует переносить на следующую строку.
 * Слишком короткие строки следует дополнять словами из следующей строки.
 * Пустые строки во входном файле обозначают конец абзаца,
 * их следует сохранить и в выходном файле
 */
fun alignFile(inputName: String, lineLength: Int, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    var currentLineLength = 0
    fun append(word: String) {
        if (currentLineLength > 0) {
            if (word.length + currentLineLength >= lineLength) {
                writer.newLine()
                currentLineLength = 0
            } else {
                writer.write(" ")
                currentLineLength++
            }
        }
        writer.write(word)
        currentLineLength += word.length
    }
    for (line in File(inputName).readLines()) {
        if (line.isEmpty()) {
            writer.newLine()
            if (currentLineLength > 0) {
                writer.newLine()
                currentLineLength = 0
            }
            continue
        }
        for (word in line.split(Regex("\\s+"))) {
            append(word)
        }
    }
    writer.close()
}

/**
 * Простая (8 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Некоторые его строки помечены на удаление первым символом _ (подчёркивание).
 * Перенести в выходной файл с именем outputName все строки входного файла, убрав при этом помеченные на удаление.
 * Все остальные строки должны быть перенесены без изменений, включая пустые строки.
 * Подчёркивание в середине и/или в конце строк значения не имеет.
 */
fun deleteMarked(inputName: String, outputName: String) {
    val inputFil = File(inputName)
    val outputFil = File(outputName).bufferedWriter()
    for (line in inputFil.readLines()) {
        if (line.isEmpty()) {
            outputFil.newLine()
            continue
        }
        if (line[0] != '_') {
            outputFil.write(line)
            outputFil.newLine()
        }
    }
    outputFil.close()
}

/**
 * Средняя (14 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * На вход подаётся список строк substrings.
 * Вернуть ассоциативный массив с числом вхождений каждой из строк в текст.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 */
fun countSubstrings(inputName: String, substrings: List<String>): Map<String, Int> {
    val result = mutableMapOf<String, Int>()
    for (separateLines in substrings.toSet()) result[separateLines] = 0
    for (line in File(inputName).readLines()) {
        for (element in substrings.toSet()) {
            var start = 0
            var index = line.toLowerCase().indexOf(element.toLowerCase(), start)
            while (index != -1) {
                result[element] = (result[element] ?: 0) + 1
                start = index + 1
                index = line.toLowerCase().indexOf(element.toLowerCase(), start)
            }

        }
    }
    return result
}


/**
 * Средняя (12 баллов)
 *
 * В русском языке, как правило, после букв Ж, Ч, Ш, Щ пишется И, А, У, а не Ы, Я, Ю.
 * Во входном файле с именем inputName содержится некоторый текст на русском языке.
 * Проверить текст во входном файле на соблюдение данного правила и вывести в выходной
 * файл outputName текст с исправленными ошибками.
 *
 * Регистр заменённых букв следует сохранять.
 *
 * Исключения (жюри, брошюра, парашют) в рамках данного задания обрабатывать не нужно
 *
 */
fun sibilants(inputName: String, outputName: String) {
    val replacement = mapOf("ы" to "и", "я" to "а", "ю" to "у", "Ы" to "И", "Я" to "А", "Ю" to "У")
    val letters = listOf("ж", "ч", "ш", "щ")
    val outputF = File(outputName).bufferedWriter()
    for (line in File(inputName).readLines()) {
        outputF.write(line[0].toString())
        for (letter in 1..line.length - 1) {
            if ((line[letter].toString() in replacement) && (line[letter - 1].toString().toLowerCase() in letters))
                outputF.write(replacement[line[letter].toString()])
            else outputF.write(line[letter].toString())
        }
        outputF.newLine()
    }
    outputF.close()
}

/**
 * Средняя (15 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по центру
 * относительно самой длинной строки.
 *
 * Выравнивание следует производить путём добавления пробелов в начало строки.
 *
 *
 * Следующие правила должны быть выполнены:
 * 1) Пробелы в начале и в конце всех строк не следует сохранять.
 * 2) В случае невозможности выравнивания строго по центру, строка должна быть сдвинута в ЛЕВУЮ сторону
 * 3) Пустые строки не являются особым случаем, их тоже следует выравнивать
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых)
 *
 */
fun centerFile(inputName: String, outputName: String) {
    val text = mutableListOf<String>()
    var maxLen = 0
    for (line in File(inputName).readLines()) {
        val newLine = line.trim()
        text.add(newLine)
        maxLen = max(maxLen, newLine.length)
    }
    File(outputName).bufferedWriter().use {
        for (line in text) {
            val currentLen = line.length
            val res = String.format("%${(maxLen + currentLen) / 2}s", line) + "\n"
            it.write(res)
        }
    }
}

/**
 * Сложная (20 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по левому и правому краю относительно
 * самой длинной строки.
 * Выравнивание производить, вставляя дополнительные пробелы между словами: равномерно по всей строке
 *
 * Слова внутри строки отделяются друг от друга одним или более пробелом.
 *
 * Следующие правила должны быть выполнены:
 * 1) Каждая строка входного и выходного файла не должна начинаться или заканчиваться пробелом.
 * 2) Пустые строки или строки из пробелов трансформируются в пустые строки без пробелов.
 * 3) Строки из одного слова выводятся без пробелов.
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых).
 *
 * Равномерность определяется следующими формальными правилами:
 * 5) Число пробелов между каждыми двумя парами соседних слов не должно отличаться более, чем на 1.
 * 6) Число пробелов между более левой парой соседних слов должно быть больше или равно числу пробелов
 *    между более правой парой соседних слов.
 *
 * Следует учесть, что входной файл может содержать последовательности из нескольких пробелов  между слвоами. Такие
 * последовательности следует учитывать при выравнивании и при необходимости избавляться от лишних пробелов.
 * Из этого следуют следующие правила:
 * 7) В самой длинной строке каждая пара соседних слов должна быть отделена В ТОЧНОСТИ одним пробелом
 * 8) Если входной файл удовлетворяет требованиям 1-7, то он должен быть в точности идентичен выходному файлу
 */
fun alignFileByWidth(inputName: String, outputName: String) {
    val sizeOfList = mutableListOf<Int>()
    val map = mutableMapOf<Int, List<String>>()
    var k = -1
    var maxLength = 0
    for (list in File(inputName).readLines()) {
        k += 1
        val words = list.trim().split(" ").filter { it != "" }
        var lengthOfWords = words.fold(0) { x, it -> x + it.length }
        if (words.isNotEmpty()) {
            lengthOfWords += words.size - 1
            map[k] = words
        } else map[k] = listOf("")
        sizeOfList.add(lengthOfWords)
        maxLength = max(maxLength, lengthOfWords)
    }
    File(outputName).bufferedWriter().use {
        for ((key, value) in map.entries) {
            if (value.size == 1) {
                it.write(value[0])
                it.newLine()
                continue
            }
            val numberOfSpaces = value.size - 1
            val t = maxLength - sizeOfList[key]
            val wholeOfSpaces = t / numberOfSpaces
            var remnantOfSpaces = t % numberOfSpaces
            for (i in 0..value.size - 2) {
                it.write(value[i] + " ".repeat(1 + wholeOfSpaces + if (remnantOfSpaces > 0) 1 else 0))
                remnantOfSpaces -= 1
            }
            it.write(value.last())
            it.newLine()
        }
    }
}

/**
 * Средняя (14 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * Вернуть ассоциативный массив, содержащий 20 наиболее часто встречающихся слов с их количеством.
 * Если в тексте менее 20 различных слов, вернуть все слова.
 * Вернуть ассоциативный массив с числом слов больше 20, если 20-е, 21-е, ..., последнее слова
 * имеют одинаковое количество вхождений (см. также тест файла input/onegin.txt).
 *
 * Словом считается непрерывная последовательность из букв (кириллических,
 * либо латинских, без знаков препинания и цифр).
 * Цифры, пробелы, знаки препинания считаются разделителями слов:
 * Привет, привет42, привет!!! -привет?!
 * ^ В этой строчке слово привет встречается 4 раза.
 *
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 * Ключи в ассоциативном массиве должны быть в нижнем регистре.
 *
 */
fun top20Words(inputName: String): Map<String, Int> = TODO()

/**
 * Средняя (14 баллов)
 *
 * Реализовать транслитерацию текста из входного файла в выходной файл посредством динамически задаваемых правил.

 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * В ассоциативном массиве dictionary содержится словарь, в котором некоторым символам
 * ставится в соответствие строчка из символов, например
 * mapOf('з' to "zz", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "yy", '!' to "!!!")
 *
 * Необходимо вывести в итоговый файл с именем outputName
 * содержимое текста с заменой всех символов из словаря на соответствующие им строки.
 *
 * При этом регистр символов в словаре должен игнорироваться,
 * но при выводе символ в верхнем регистре отображается в строку, начинающуюся с символа в верхнем регистре.
 *
 * Пример.
 * Входной текст: Здравствуй, мир!
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Пример 2.
 *
 * Входной текст: Здравствуй, мир!
 * Словарь: mapOf('з' to "zZ", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "YY", '!' to "!!!")
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun transliterate(inputName: String, dictionary: Map<Char, String>, outputName: String) {
    TODO()
}

/**
 * Средняя (12 баллов)
 *
 * Во входном файле с именем inputName имеется словарь с одним словом в каждой строчке.
 * Выбрать из данного словаря наиболее длинное слово,
 * в котором все буквы разные, например: Неряшливость, Четырёхдюймовка.
 * Вывести его в выходной файл с именем outputName.
 * Если во входном файле имеется несколько слов с одинаковой длиной, в которых все буквы разные,
 * в выходной файл следует вывести их все через запятую.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 * Пример входного файла:
 * Карминовый
 * Боязливый
 * Некрасивый
 * Остроумный
 * БелогЛазый
 * ФиолетОвый

 * Соответствующий выходной файл:
 * Карминовый, Некрасивый
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun chooseLongestChaoticWord(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная (22 балла)
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе элементы текстовой разметки следующих типов:
 * - *текст в курсивном начертании* -- курсив
 * - **текст в полужирном начертании** -- полужирный
 * - ~~зачёркнутый текст~~ -- зачёркивание
 *
 * Следует вывести в выходной файл этот же текст в формате HTML:
 * - <i>текст в курсивном начертании</i>
 * - <b>текст в полужирном начертании</b>
 * - <s>зачёркнутый текст</s>
 *
 * Кроме того, все абзацы исходного текста, отделённые друг от друга пустыми строками, следует обернуть в теги <p>...</p>,
 * а весь текст целиком в теги <html><body>...</body></html>.
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 * Отдельно следует заметить, что открывающая последовательность из трёх звёздочек (***) должна трактоваться как "<b><i>"
 * и никак иначе.
 *
 * При решении этой и двух следующих задач полезно прочитать статью Википедии "Стек".
 *
 * Пример входного файла:
Lorem ipsum *dolor sit amet*, consectetur **adipiscing** elit.
Vestibulum lobortis, ~~Est vehicula rutrum *suscipit*~~, ipsum ~~lib~~ero *placerat **tortor***,

Suspendisse ~~et elit in enim tempus iaculis~~.
 *
 * Соответствующий выходной файл:
<html>
<body>
<p>
Lorem ipsum <i>dolor sit amet</i>, consectetur <b>adipiscing</b> elit.
Vestibulum lobortis. <s>Est vehicula rutrum <i>suscipit</i></s>, ipsum <s>lib</s>ero <i>placerat <b>tortor</b></i>.
</p>
<p>
Suspendisse <s>et elit in enim tempus iaculis</s>.
</p>
</body>
</html>
 *
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlSimple(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная (23 балла)
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе набор вложенных друг в друга списков.
 * Списки бывают двух типов: нумерованные и ненумерованные.
 *
 * Каждый элемент ненумерованного списка начинается с новой строки и символа '*', каждый элемент нумерованного списка --
 * с новой строки, числа и точки. Каждый элемент вложенного списка начинается с отступа из пробелов, на 4 пробела большего,
 * чем список-родитель. Максимально глубина вложенности списков может достигать 6. "Верхние" списки файла начинются
 * прямо с начала строки.
 *
 * Следует вывести этот же текст в выходной файл в формате HTML:
 * Нумерованный список:
 * <ol>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ol>
 *
 * Ненумерованный список:
 * <ul>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ul>
 *
 * Кроме того, весь текст целиком следует обернуть в теги <html><body><p>...</p></body></html>
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 *
 * Пример входного файла:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
 * Утка по-пекински
 * Утка
 * Соус
 * Салат Оливье
1. Мясо
 * Или колбаса
2. Майонез
3. Картофель
4. Что-то там ещё
 * Помидоры
 * Фрукты
1. Бананы
23. Яблоки
1. Красные
2. Зелёные
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 *
 *
 * Соответствующий выходной файл:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
<html>
<body>
<p>
<ul>
<li>
Утка по-пекински
<ul>
<li>Утка</li>
<li>Соус</li>
</ul>
</li>
<li>
Салат Оливье
<ol>
<li>Мясо
<ul>
<li>Или колбаса</li>
</ul>
</li>
<li>Майонез</li>
<li>Картофель</li>
<li>Что-то там ещё</li>
</ol>
</li>
<li>Помидоры</li>
<li>Фрукты
<ol>
<li>Бананы</li>
<li>Яблоки
<ol>
<li>Красные</li>
<li>Зелёные</li>
</ol>
</li>
</ol>
</li>
</ul>
</p>
</body>
</html>
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlLists(inputName: String, outputName: String) {
    TODO()
}

/**
 * Очень сложная (30 баллов)
 *
 * Реализовать преобразования из двух предыдущих задач одновременно над одним и тем же файлом.
 * Следует помнить, что:
 * - Списки, отделённые друг от друга пустой строкой, являются разными и должны оказаться в разных параграфах выходного файла.
 *
 */
fun markdownToHtml(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя (12 баллов)
 *
 * Вывести в выходной файл процесс умножения столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 111):
19935
 *    111
--------
19935
+ 19935
+19935
--------
2212785
 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 * Нули в множителе обрабатывать так же, как и остальные цифры:
235
 *  10
-----
0
+235
-----
2350
 *
 */
fun printMultiplicationProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}


/**
 * Сложная (25 баллов)
 *
 * Вывести в выходной файл процесс деления столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 22):
19935 | 22
-198     906
----
13
-0
--
135
-132
----
3

 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 *
 */
fun printDivisionProcess(lhv: Int, rhv: Int, outputName: String) {

    val result = (lhv / rhv).toString()
    val finalRem = (lhv % rhv).toString()
    val meanings = mutableListOf<String>() // список значений которые получатся после очередной разности
    val negations = mutableListOf<String>() // список значений на которые будут убавляться значения из списка meaning
    val remnants = mutableListOf<String>() // список остатков
    var end = 1
    while (lhv.toString().substring(0, end).toInt() < rhv * result[0].toString().toInt()) end += 1
    negations.add((rhv * result[0].toString().toInt()).toString())
    if (result.length != 1) {
        var meaning = (lhv.toString().substring(0, end).toInt() - rhv * result[0].toString().toInt()).toString() +
                lhv.toString()[end].toString() // значение после разности и прибавления к нему цифры из lhv
        remnants.add((lhv.toString().substring(0, end).toInt() - rhv * result[0].toString().toInt()).toString())
        meanings.add(meaning)
        var x = end + 1
        var i = 1
        while (x < lhv.toString().length) {
            meanings.add(
                (meaning.toInt() - rhv * result[i].toString().toInt()).toString() + lhv.toString()[x].toString()
            )
            negations.add((rhv * result[i].toString().toInt()).toString())
            remnants.add((meaning.toInt() - rhv * result[i].toString().toInt()).toString())
            meaning =
                (meaning.toInt() - rhv * result[i].toString().toInt()).toString() + lhv.toString()[x].toString()
            x++
            i++
        }
        negations.add((rhv * result[i].toString().toInt()).toString())
    } else negations.add((rhv * result.toInt()).toString())
    for ((index, element) in negations.withIndex()) negations[index] = "-$element"
    // после того, как все необходимые значения были созданы, можно приступить к записи их в файл с нужным форматом
    File(outputName).bufferedWriter().use {
        var space = 0
        if (lhv.toString().length == negations[0].length && result.length == 1) space += 1
        it.write(" $lhv | $rhv\n")
        print(" $lhv | $rhv\n")
        it.write(" ".repeat(space) + negations[0] + " ".repeat(lhv.toString().length - negations[0].length + 1 - space) + "   $result\n")
        print(" ".repeat(space) + negations[0] + " ".repeat(lhv.toString().length - negations[0].length + 1 - space) + "   $result\n")
        it.write(" ".repeat(space) + "-".repeat(negations[0].length) + "\n")
        print(" ".repeat(space) + "-".repeat(negations[0].length) + "\n")
        var flag = true
        // после внесения основы можем приступить к внесению последующих операции
        for (i in meanings.indices) {
            if (i == 0) space += negations[0].length - remnants[0].length
            else space += meanings[i - 1].length - remnants[i].length
            it.write(" ".repeat(space) + meanings[i] + "\n")
            print(" ".repeat(space) + meanings[i] + "\n")
            space += meanings[i].length - negations[i + 1].length
            it.write(" ".repeat(space) + negations[i + 1] + "\n")
            print(" ".repeat(space) + negations[i + 1] + "\n")
            if (meanings[i].length > negations[i + 1].length) {
                space -= meanings[i].length - negations[i + 1].length
                it.write(" ".repeat(space) + "-".repeat(meanings[i].length) + "\n")
                print(" ".repeat(space) + "-".repeat(meanings[i].length) + "\n")
            } else {
                it.write(" ".repeat(space) + "-".repeat(negations[i + 1].length) + "\n")
                print(" ".repeat(space) + "-".repeat(negations[i + 1].length) + "\n")
                if (negations[i + 1].length > meanings[i].length) space++
            }
            if (i == meanings.size - 1) {
                space += meanings[i].length - finalRem.length
                it.write(" ".repeat(space) + finalRem + "\n")
                print(" ".repeat(space) + finalRem + "\n")
                flag = false
            }

        }
        //для случаев когда была лишь одна разность
        if (flag) {
            if (lhv.toString().length < negations[0].length) space++
            space += lhv.toString().length - finalRem.length
            it.write(" ".repeat(space) + finalRem + "\n")
            print(" ".repeat(space) + finalRem + "\n")
        }
    }
}


//File(outputName).bufferedWriter().use {
//        var probel = 0
//        if (lhv.toString().length == negations[0].length && result.length == 1) probel += 1
//        it.write(" $lhv | $rhv\n")
//        print(" $lhv | $rhv\n")
//        it.write(" ".repeat(probel) + negations[0] + " ".repeat(lhv.toString().length - negations[0].length + 1) + "   $result\n")
//        print(" ".repeat(probel) + negations[0] + " ".repeat(lhv.toString().length - negations[0].length + 1) + "   $result\n")
//        it.write(" ".repeat(probel) + "-".repeat(negations[0].length) + "\n")
//        print(" ".repeat(probel) + "-".repeat(negations[0].length) + "\n")
//        // после внесения основы можем приступить к внесению последующих операции
//        var proverka = false
//        for (i in meanings.indices) {
//            proverka = false
//            probel += negations[i].length - remnants[i].length
//            it.write(" ".repeat(probel) + meanings[i] + "\n")
//            print(" ".repeat(probel) + meanings[i] + "\n")
//            if (negations[i + 1].length > meanings[i].length) {
//                probel--
//                proverka = true
//            } // если уменьшаемое и вычитаемое по количеству цифр одинаковы, то минус должен стоять левее
//            it.write(" ".repeat(probel) + negations[i + 1] + "\n")
//            print(" ".repeat(probel) + negations[i + 1] + "\n")
//            it.write(" ".repeat(probel) + "-".repeat(negations[i + 1].length) + "\n")
//            print(" ".repeat(probel) + "-".repeat(negations[i + 1].length) + "\n")
//        }
//        if (probel == 0 && !proverka) {
//            it.write(" ".repeat(negations[0].length - finalRem.toString().length) + "$finalRem")
//            println(" ".repeat(negations[0].length - finalRem.toString().length) + "$finalRem")
//        } else {
//            probel += negations[negations.size - 1].length - finalRem.toString().length
//            it.write(" ".repeat(probel) + "$finalRem")
//            println(" ".repeat(probel) + "$finalRem")
//        }
//
//    }

