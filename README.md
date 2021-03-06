
# Sveta-Babareko-Academy-Test-Task
--------------------------------------------------------------------------------------------------------------------
Тестовая задача для стажировки в Syberry Academy
<p>Вы — инженер, проектирующий роверы-беспилотники. Вам надо спроектировать путь ровера по заранее известной местности с максимальной экономией заряда.

Местность
Вам пришли данные о местности в закодированном виде: фотография, сконвертированная в матрицу с числами. Одна матрица — это прямоугольный снимок размером х на y метров. Вот пример одной такой сконвертированной фотографии, на ней снимок в 100 на 100 метров:
<br>Фото 1:
<br>0 2 3 4 1
<br>2 3 4 4 1
<br>3 4 5 6 2
<br>4 5 6 7 1
<br>6 7 8 7 1
<p>Числа показывают высоту над уровнем моря. 0 — это высота ровно на уровне моря, а, например, 4 — это 4 единицы над уровнем моря. На Фото 1 закодирован холм, пологий слева и резко обрывающийся справа.
<br>Небольшой холмик выглядел бы вот так
<br>Фото 2:
<br>0 1 1 1 0
<br>1 1 3 1 1
<br>0 1 1 1 0
<br>0 0 0 0 0
<p>А вот так: ложбина между двумя холмами
<br>Фото 3:
<br>1 1 2 3 4
<br>1 0 1 2 3
<br>2 1 1 1 2
<br>3 3 1 0 1
<br>4 3 1 1 0
<p>На этих данных - скала или овраг, так как виден очень резкий перепад высот в середине снимка
<br>Фото 4:
<br>1 1 6 7 7
<br>1 1 6 7 8
<br>1 6 7 8 9
<p>А на этом - маленькая ямка
<br>Фото 5:
<br>3 4 4 4 4 3
<br>3 2 1 1 1 4
<br>4 2 1 1 3 4
<br>4 4 2 2 3 4
<p>Данные придут вам в виде матрицы с неотрицательными числами. Размер матрицы NxM.

Ровер
Ровер всегда движется из верхней левой точки [0][0] в правую нижнюю точку [N - 1][M - 1], где N и M - это длина и ширина матрицы. Это надо для того, чтобы разрезать фотографию на одинаковые куски, обработать их по-отдельности, а потом склеить весь путь.
У вашего ровера есть несколько ограничений:

Движение
<br>Из любой точки ровер может двигаться только в четыре стороны: на север, юг, запад, восток. Ровер не может ехать по-диагонали — эта функция еще не реализована. Ровер не может вернуться в ту точку, в которой уже был.
<br>Заряд
<br>Ровер ездит на заряде. Вы знаете, что для ровера очень затратно подниматься и опускаться. Он тратит единицу заряда на само движение, и дополнительные единицы на подъем и спуск. Ровер бы вообще спокойно жил, если бы ездил по асфальту в Беларуси, тогда бы он тратил себе линейно заряд и в ус не дул, но жизнь его сложилась иначе.
<br>Расход заряда
<br>Заряд расходуется по правилу:
<br>На 1 шаг ровер всегда тратит 1 единицу заряда. На подъем или спуск ровер тратит заряд, пропорциональный сложности подъема или спуска. Сложность подъема или спуска - это разница между высотами.


<p>Например, в такой местности
<br>1 2
<br>1 5
<br>на путь из [0][0] в [0][1] ровер потратит 2 единицы заряд: 1 единица заряда на само движение, и еще 1 единицу заряда на подъем в [0][1]. А из [0][1] в [1][1] ровер потратит 4 единицы заряда: 1 единица на само движение, и 3 единицы (5 - 2) на подъем
<br>Вам надо рассчитать путь ровера из верхей левой [0][0] точки в правую нижнюю [N - 1][M - 1] точку с минимальной тратой заряда.
<br>Вы не заранее знаете размер фотографии, которую будете обрабатывать, N и M - произвольные неотрицательные числа.

<p>План
<br>Сделайте план пути и планируемый расход в txt файле. Назовите файл path-plan.txt
<br>Для фотографии
<br>0 4
<br>1 3
<br>план будет такой:
<br>path-plan.txt
<br>[0][0]->[1][0]->[1][1]
<br>steps: 2
<br>fuel: 5
<br>Ровер едет из 0 в 1 в 3, сделает два шага, потратит 5 заряда. Если бы он поехал сначала в 4, потом в 3, он бы сделал то же количество шагов, но потратил бы 7 заряда. <br>Оптимальный путь: 2 шага и 5 заряда.
<br>Если на карте есть несколько вариантов пути, выберите любой из них.

<p>Требования
<br>Реализуйте функцию calculateRoverPath(map) по требованиям.
<br>Результат запишите в txt файл.
<br>Используйте готовые файлы для решения. Не меняйте название файла, название методов и параметров.
<br>Напишите для себя .gitignor. Не добавляйте в репозиторий .idea, src, venv, DS_STORE и подобные файлы. В репозитории должен лежать только файл с решением и README.md

<p>Class name: Rover
<br>Метод calculateRoverPath должен быть static
<br>Не добавляйте packages
<br>Версия Java 14+
<br>Не используйте System.out.*, Console.Write* и любые другие методы для выведения данных в консоль

<p>Ограничения
<br>Нельзя использовать библиотеки для реализации алгоритма. Реализовать алгоритм вы можете только своими силами.
<br>Можно использовать библиотеки для записи в файл.

<p>Рефакторинг(доп. задание 2ого этапа):
<br>1. Ровер может ездить и по-диагонали
<br>2. Ровер может опускаться на высоту ниже уровня моря, т.е. возможны отрицательные значения
<br>3. Входной массив изменен на String: public static void calculateRoverPath(String[][] map), т.е. входные данные должны быть:
<br>   {{"1", "1", "X", "X", "X"},
<br>   {"1", "1", "X", "X", "8"},
<br>   {"1", "1", "0", "0", "3"}}
<br>4. Если передано значение "X" - это значит, что ровер не может в эту точку лететь.
<br>5. Если данные переданны не корректно, или точка [0,0] = "X" или по любой другой причине ровер не может обработать данные и прилететь в конечную точку - нужно обработать исключение:CannotStartMovement.
<br>6. Исключение должно выводить в файл path-plan.txt "Cannot start a movement because data is incorrect." и выходить из программы.

<p>Рефакторинг
Добавлены тесты для тестирования программы.

