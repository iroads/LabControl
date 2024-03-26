package ru.asphaltica.LabControl.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.asphaltica.LabControl.models.mixComponents.Bitumen;
import ru.asphaltica.LabControl.models.mixComponents.Mineral;
import ru.asphaltica.LabControl.util.Sito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class HotMixTestResultTest {

    private final Map<Sito, Double> expectedCHOPS = new HashMap<>();
    private final Map<Sito, Double> expectedPP = new HashMap<>();
    private final Map<Sito, Double> expectedZero = new HashMap<>();

    HotMixTestResult result;

    {
        expectedCHOPS.put(Sito.DNO, 5.5);
        expectedCHOPS.put(Sito.S0063, 7.8);
        expectedCHOPS.put(Sito.S0125, 9.17);
        expectedCHOPS.put(Sito.S025, 6.88);
        expectedCHOPS.put(Sito.S05, 5.96);
        expectedCHOPS.put(Sito.S1, 6.42);
        expectedCHOPS.put(Sito.S2, 6.88);
        expectedCHOPS.put(Sito.S4, 9.17);
        expectedCHOPS.put(Sito.S56, 8.72);
        expectedCHOPS.put(Sito.S8, 8.26);
        expectedCHOPS.put(Sito.S112, 7.8);
        expectedCHOPS.put(Sito.S16, 6.88);
        expectedCHOPS.put(Sito.S224, 4.59);
        expectedCHOPS.put(Sito.S315, 5.96);

        expectedPP.put(Sito.DNO, 0.0);
        expectedPP.put(Sito.S0063, 5.5);
        expectedPP.put(Sito.S0125, 13.30);
        expectedPP.put(Sito.S025, 22.47);
        expectedPP.put(Sito.S05, 29.35);
        expectedPP.put(Sito.S1, 35.31);
        expectedPP.put(Sito.S2, 41.73);
        expectedPP.put(Sito.S4, 48.61);
        expectedPP.put(Sito.S56, 57.78);
        expectedPP.put(Sito.S8, 66.50);
        expectedPP.put(Sito.S112, 74.76);
        expectedPP.put(Sito.S16, 82.56);
        expectedPP.put(Sito.S224, 89.44);
        expectedPP.put(Sito.S315, 94.03);

        expectedZero.put(Sito.DNO, 0.0);
        expectedZero.put(Sito.S0063, 0.0);
        expectedZero.put(Sito.S0125, 0.0);
        expectedZero.put(Sito.S025, 0.0);
        expectedZero.put(Sito.S05, 0.0);
        expectedZero.put(Sito.S1, 0.0);
        expectedZero.put(Sito.S2, 0.0);
        expectedZero.put(Sito.S4, 0.0);
        expectedZero.put(Sito.S56, 0.0);
        expectedZero.put(Sito.S8, 0.0);
        expectedZero.put(Sito.S112, 0.0);
        expectedZero.put(Sito.S16, 0.0);
        expectedZero.put(Sito.S224, 0.0);
        expectedZero.put(Sito.S315, 0.0);
    }

    @BeforeEach
    void setUp() {
        result = new HotMixTestResult();
        result.setDno(120);
        result.setChog0063(170);
        result.setChog0125(200);
        result.setChog025(150);
        result.setChog05(130);
        result.setChog1(140);
        result.setChog2(150);
        result.setChog4(200);
        result.setChog56(190);
        result.setChog8(180);
        result.setChog112(170);
        result.setChog16(150);
        result.setChog224(100);
        result.setChog315(130);

        result.setGravityMixBulk(2.511);
        result.setGravityMixMaximum(2.599);

        result.setWeightOfBasket(5000.2);
        result.setWeightOfBasketAndMix(10000.8);
        result.setWeighOfBasketAndMixAfterBurn(9853.9);

        result.setCorrectionBitumenStoneBurn(0.3);
        result.setBitumenPercentageIn100User(4.5);

        Recipe recipe = new Recipe();
        Bitumen bitumen = new Bitumen();
        bitumen.setPercentageUp100(5);
        recipe.setBitumen(bitumen);

        Mineral powder = new Mineral();
        powder.setGravityStoneApparent(2.730);
        powder.setPercentage(2);
        recipe.setPowder(powder);

        Mineral stoneFine1 = new Mineral();
        stoneFine1.setGravityStoneApparent(2.748);
        stoneFine1.setPercentage(25);
        recipe.setStoneFine1(stoneFine1);

        Mineral stoneFine2 = new Mineral();
        stoneFine2.setGravityStoneApparent(2.745);
        stoneFine2.setPercentage(13);
        recipe.setStoneFine2(stoneFine2);

        Mineral stoneCoarse1 = new Mineral();
        stoneCoarse1.setGravityStoneAverage(2.661);
        stoneCoarse1.setPercentage(5);
        recipe.setStoneCoarse1(stoneCoarse1);

        Mineral stoneCoarse2 = new Mineral();
        stoneCoarse2.setGravityStoneAverage(2.662);
        stoneCoarse2.setPercentage(7);
        recipe.setStoneCoarse2(stoneCoarse2);

        Mineral stoneCoarse3 = new Mineral();
        stoneCoarse3.setGravityStoneAverage(2.663);
        stoneCoarse3.setPercentage(9);
        recipe.setStoneCoarse3(stoneCoarse3);

        Mineral stoneCoarse4 = new Mineral();
        stoneCoarse4.setGravityStoneAverage(2.664);
        stoneCoarse4.setPercentage(11);
        recipe.setStoneCoarse4(stoneCoarse4);

        Mineral stoneCoarse5 = new Mineral();
        stoneCoarse5.setGravityStoneAverage(2.665);
        stoneCoarse5.setPercentage(13);
        recipe.setStoneCoarse5(stoneCoarse5);

        Mineral stoneCoarse6 = new Mineral();
        stoneCoarse6.setGravityStoneAverage(2.666);
        stoneCoarse6.setPercentage(15);
        recipe.setStoneCoarse6(stoneCoarse6);

        recipe.setGravityMixMaximum(2.599);

        Batch batch = new Batch();
        batch.setRecipeSource(recipe);
        result.setBatchSource(batch);
    }

    @Test
    void getCHOPS() {
        Map<Sito, Double> actualCHOPS = result.getCHOPS();
        actualCHOPS.entrySet().stream().forEach(entry -> assertEquals(expectedCHOPS.get(entry.getKey()), entry.getValue()));
    }

    @Test
    void getPP() {
        Map<Sito, Double> actualPP = result.getPP();
        actualPP.entrySet().stream().forEach(entry -> assertEquals(expectedPP.get(entry.getKey()), entry.getValue()));
    }

    @Test
    void chopsShouldZero() {
        setChogsZero();
        Map<Sito, Double> actualZero = result.getCHOPS();
        actualZero.entrySet().stream().forEach(entry -> assertEquals(expectedZero.get(entry.getKey()), entry.getValue()));
    }

    @Test
    void ppShouldZero() {
        setChogsZero();
        Map<Sito, Double> actualZero = result.getPP();
        actualZero.entrySet().stream().forEach(entry -> assertEquals(expectedZero.get(entry.getKey()), entry.getValue()));
    }

    @Test
    void getVoids() {
        assertEquals(3.4, result.getVoids());
    }

    @Test
    void voidsShouldZero() {
        result.setGravityMixBulk(0);
        result.setGravityMixMaximum(0);
        assertEquals(0.0, result.getVoids());
    }

    @Test
    void getBitumenPercentageIn100Burn() {
        assertEquals(2.94, result.getBitumenPercentageIn100Burn());
    }

    @Test
    void getBitumenPercentageIn100Gmm() {
        assertEquals(4.76, result.getBitumenPercentageIn100Gmm());
    }

    @Test
    void getBitumenPercentageIn100BurnCorr() {
        assertEquals(2.64, result.getBitumenPercentageIn100BurnCorr());
    }

    @Test
    void getPStone() {
        assertEquals(0.9455, result.getPStone(5.45));
    }

    @Test
    void getPMZ() {
        assertEquals(10.6, result.getPMZ(result.getPStone(4.03)));
    }

    @Test
    void getPNB() {
        assertEquals(68.5, result.getPNB(10.8));
    }

    @Test
    void getPMZ_BitumenBurn() {
        assertEquals(9.6, result.getPMZ_BitumenBurn());
    }

    @Test
    void getPNB_BitumenBurn() {
        assertEquals(64.6, result.getPNB_BitumenBurn());
    }

    @Test
    void getPMZ_BitumenGmm() {
        assertEquals(11.3, result.getPMZ_BitumenGmm());
    }

    @Test
    void getPNB_BitumenGmm() {
        assertEquals(69.9, result.getPNB_BitumenGmm());
    }

    @Test
    void getPMZ_BitumenBurnCorr() {
        assertEquals(9.3, result.getPMZ_BitumenBurnCorr());
    }

    @Test
    void getPNB_BitumenBurnCorr() {
        assertEquals(63.4, result.getPNB_BitumenBurnCorr());
    }

    @Test
    void getPMZ_BitumenUser() {
        assertEquals(11.1, result.getPMZ_BitumenUser());
    }

    @Test
    void getPNB_BitumenUser() {
        assertEquals(69.4, result.getPNB_BitumenUser());
    }

    private void setChogsZero() {
        result.setDno(0);
        result.setChog0063(0);
        result.setChog0125(0);
        result.setChog025(0);
        result.setChog05(0);
        result.setChog1(0);
        result.setChog2(0);
        result.setChog4(0);
        result.setChog56(0);
        result.setChog8(0);
        result.setChog112(0);
        result.setChog16(0);
        result.setChog224(0);
        result.setChog315(0);
    }
}