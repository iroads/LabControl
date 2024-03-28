package ru.asphaltica.LabControl.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.asphaltica.LabControl.models.mixComponents.Bitumen;
import ru.asphaltica.LabControl.models.mixComponents.Mineral;
import ru.asphaltica.LabControl.util.enums.MixLayer;
import ru.asphaltica.LabControl.util.enums.MixTraffic;
import ru.asphaltica.LabControl.util.enums.MixType;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {

    private Recipe recipe;

    @BeforeEach
    void setUp(){
        this.recipe = new Recipe();
        recipe.setMixType(MixType.A8);
        recipe.setMixLayer(MixLayer.N);
        recipe.setMixTraffic(MixTraffic.N);

        recipe.setGravityMixBulk(2.511);
        recipe.setGravityMixMaximum(2.599);

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


    }

    @Test
    void getMixTitle() {
        String expectedTitle = "А8НН";
        assertEquals(expectedTitle, recipe.getMixTitle());
    }

    @Test
    void getVoids() {
        assertEquals(3.4, recipe.getVoids());
    }

    @Test
    void getBitumenPercentageIn100() {
        assertEquals(4.76, recipe.getBitumenPercentageIn100());
    }

    @Test
    void getGravityStoneBulk() {
        assertEquals(2.696, recipe.getGravityStoneBulk());
    }

    @Test
    void getPMZ(){
        assertEquals(11.3, recipe.getPMZ());
    }

    @Test
    void getPNB() {
        assertEquals(69.9, recipe.getPNB());
    }

    @Test
    void getPStone(){
        assertEquals(0.9524, recipe.getPStone());
    }

    @Test
    void getGravityStoneEffective() {
        assertEquals(2.825, recipe.getGravityStoneEffective());
    }

}