package org.jurassicraft.client.render.renderdef;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.client.model.animation.AnimationVelociraptor;
import org.jurassicraft.common.entity.base.EnumGrowthStage;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;

@SideOnly(Side.CLIENT)
public class RenderDefVelociraptor extends RenderDinosaurDefinition
{
    private IModelAnimator animator;
    private ModelJson model;

    private ModelJson infant;
    private ModelJson juvenile;
    private ModelJson adolescent;

    public RenderDefVelociraptor()
    {
        super(JCEntityRegistry.velociraptor);

        this.animator = new AnimationVelociraptor();

        try
        {
            String texture = "/assets/jurassicraft/models/entities/velociraptor/" + getDinosaur().getName(0).toLowerCase().replaceAll(" ", "_");

            this.model = getTabulaModel(texture, 0);
            this.infant = getTabulaModel(texture + "_infant", 0);
            this.juvenile = getTabulaModel(texture + "_juvenile", 0);
            this.adolescent = getTabulaModel(texture + "_adolescent", 0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public ModelBase getModel(int geneticVariant, EnumGrowthStage stage)
    {
        switch (stage)
        {
            case INFANT:
                return infant;
            case JUVENILE:
                return juvenile;
            case ADOLESCENT:
                return adolescent;
            default:
                return model;
        }
    }

    @Override
    public float getAdultScaleAdjustment()
    {
        return 1F;
    }

    @Override
    public float getBabyScaleAdjustment()
    {
        return 0.3F;
    }

    @Override
    public float getShadowSize()
    {
        return 0.65F;
    }

    @Override
    public IModelAnimator getModelAnimator(int geneticVariant)
    {
        return animator;
    }
}