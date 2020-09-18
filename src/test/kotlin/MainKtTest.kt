import org.junit.Assert
import org.junit.Test

internal class MainKtTest {

    //region calcVisaPeace
    @Test
    fun calcVisaPeace_SmallAmount() {
        val transferSize = 10000;

        val res = calcVisaPeace(transferSize);

        Assert.assertEquals(6500, res);
    }

    @Test
    fun calcVisaPeace_VerySmallAmount() {
        val transferSize = 100;

        val res = calcVisaPeace(transferSize);

        Assert.assertEquals(0, res);
    }

    @Test
    fun calcVisaPeace_LargeAmount() {
        val transferSize = 1000000;

        val res = calcVisaPeace(transferSize);

        Assert.assertEquals(992500, res);
    }

    //endregion calcVisaPeace

    //region calcMastercardMaestro
    @Test
    fun calcMastercardMaestro_SmallAmountSmallMonth() {
        val transferSize = 100_00;
        val thisMonthTransfers = 1000_00;

        val res = calcMastercardMaestro(thisMonthTransfers, transferSize);

        Assert.assertEquals(7940, res);
    }

    @Test
    fun calcMastercardMaestro_VerySmallAmountSmallMonth() {
        val transferSize = 1_00;
        val thisMonthTransfers = 1000_00;

        val res = calcMastercardMaestro(thisMonthTransfers, transferSize);

        Assert.assertEquals(0, res);
    }

    @Test
    fun calcMastercardMaestro_LargeAmountSmallMonth() {
        val transferSize = 1000_00;
        val thisMonthTransfers = 1000_00;

        val res = calcMastercardMaestro(thisMonthTransfers, transferSize);

        Assert.assertEquals(transferSize, res);
    }

    @Test
    fun calcMastercardMaestro_SmallAmountLargeMonth() {
        val transferSize = 100_00;
        val thisMonthTransfers = 100000_00;

        val res = calcMastercardMaestro(thisMonthTransfers, transferSize);

        Assert.assertEquals(7940, res);
    }

    @Test
    fun calcMastercardMaestro_LargeAmountLargeMonth() {
        val transferSize = 1000_00;
        val thisMonthTransfers = 100000_00;

        val res = calcMastercardMaestro(thisMonthTransfers, transferSize);

        Assert.assertEquals(97400, res);
    }

    //endregion calcMastercardMaestro

    //region calcAfterCommission

    @Test
    fun calcAfterCommission_Mastercard(){
        val cardType = "Mastercard";
        val thisMonthTransfers = 123
        val transferSize = 456

        val res = calcAfterCommission(cardType, thisMonthTransfers, transferSize)

        Assert.assertEquals(calcMastercardMaestro(thisMonthTransfers, transferSize), res)
    }

    @Test
    fun calcAfterCommission_Maestro(){
        val cardType = "Maestro";
        val thisMonthTransfers = 123
        val transferSize = 456

        val res = calcAfterCommission(cardType, thisMonthTransfers, transferSize)

        Assert.assertEquals(calcMastercardMaestro(thisMonthTransfers, transferSize), res)
    }

    @Test
    fun calcAfterCommission_Visa(){
        val cardType = "Visa";
        val thisMonthTransfers = 123
        val transferSize = 456

        val res = calcAfterCommission(cardType, thisMonthTransfers, transferSize)

        Assert.assertEquals(calcVisaPeace(transferSize), res)
    }

    @Test
    fun calcAfterCommission_Peace(){
        val cardType = "Мир";
        val thisMonthTransfers = 123
        val transferSize = 456

        val res = calcAfterCommission(cardType, thisMonthTransfers, transferSize)

        Assert.assertEquals(calcVisaPeace(transferSize), res)
    }

    @Test
    fun calcAfterCommission_Vk(){
        val cardType = "Vk";
        val thisMonthTransfers = 123
        val transferSize = 456

        val res = calcAfterCommission(cardType, thisMonthTransfers, transferSize)

        Assert.assertEquals(transferSize, res)
    }

    //endregion calcAfterCommission

}