const val val75000 = 75000_00
const val val20 = 20_00
const val val006 = 0.006
const val val0075 = 0.0075
const val val35 = 35_00
const val val300 = 300_00


fun main() {
    calcAndPrint("Mastercard", 50000_00, 1000_00)
    calcAndPrint("Mastercard", 150000_00, 1000_00)
    calcAndPrint("Maestro", 50000_00, 1000_00)
    calcAndPrint("Maestro", 150000_00, 1000_00)
    calcAndPrint("Visa", 50000_00, 100_00)
    calcAndPrint("Visa", 50000_00, 10000_00)
    calcAndPrint("Мир", 50000_00, 100_00)
    calcAndPrint("Мир", 50000_00, 10000_00)
    calcAndPrint("vk", 50000_00, 1000_00)
    calcAndPrint("vk", 50000_00, 1000_00)
}

fun calcAndPrint(cardType: String, thisMonthTransfers: Int, transferSize: Int) {
    val afterCommission = calcAfterCommission(cardType, thisMonthTransfers, transferSize);
    println(
        "Карточка: $cardType\n"
                + "Переводы за месяц: $thisMonthTransfers коп\n"
                + "Размер переводимой суммы: $transferSize коп\n"
                + "После комиссии: $afterCommission коп\n"
    );
}

fun calcAfterCommission(cardType: String, thisMonthTransfers: Int, transferSize: Int): Int {
    return when {
        cardType == "Mastercard" || cardType == "Maestro" -> calcMastercardMaestro(thisMonthTransfers, transferSize)
        cardType == "Visa" || cardType == "Мир" -> calcVisaPeace(transferSize)
        else -> -1//transferSize
    }
}

fun calcVisaPeace(transferSize: Int): Int {
    val res = if (transferSize * val0075 < val35) transferSize - val35 else (transferSize * (1 - val0075)).toInt();
    return if (res > 0) res else 0;
}

fun calcMastercardMaestro(thisMonthTransfers: Int, transferSize: Int): Int {
    val res = if (thisMonthTransfers <= val75000 && transferSize >= val300) transferSize else (transferSize * (1 - val006) - val20).toInt();
    return if (res > 0) res else 0;
}