package org.jurassicraft.server.message;

import io.netty.buffer.ByteBuf;
import net.ilexiconn.llibrary.server.network.AbstractMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.server.vehicles.helicopter.HelicopterBaseEntity;
import org.jurassicraft.server.vehicles.helicopter.modules.EnumModulePosition;
import org.jurassicraft.server.vehicles.helicopter.modules.HelicopterModuleSpot;

public class HelicopterModulesMessage extends AbstractMessage<HelicopterModulesMessage>
{
    private NBTTagCompound compound;
    private EnumModulePosition pos;
    private HelicopterModuleSpot spot;
    private int heliID;

    public HelicopterModulesMessage()
    {
    }

    public HelicopterModulesMessage(int heliID, EnumModulePosition pos, HelicopterModuleSpot spot)
    {
        this.heliID = heliID;
        this.pos = pos;
        this.spot = spot;
        compound = new NBTTagCompound();
        spot.writeToNBT(compound);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onClientReceived(Minecraft client, HelicopterModulesMessage message, EntityPlayer player, MessageContext messageContext)
    {
        HelicopterBaseEntity helicopter = HelicopterMessages.getHeli(player.worldObj, message.heliID);
        if (helicopter != null)
        {
            System.out.println(message.heliID);
            HelicopterModuleSpot spot = helicopter.getModuleSpot(message.pos);
            spot.readFromNBT(message.compound);
            System.out.println(message.compound);
        }
    }

    @Override
    public void onServerReceived(MinecraftServer server, HelicopterModulesMessage message, EntityPlayer player, MessageContext messageContext)
    {
        HelicopterBaseEntity helicopter = HelicopterMessages.getHeli(player.worldObj, message.heliID);
        if (helicopter != null)
        {
            System.out.println(message.heliID);
            HelicopterModuleSpot spot = helicopter.getModuleSpot(message.pos);
            spot.readFromNBT(message.compound);
            System.out.println(message.compound);
        }
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        heliID = buf.readInt();
        pos = EnumModulePosition.values()[buf.readInt()];
        compound = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(heliID);
        buf.writeInt(pos.ordinal());
        ByteBufUtils.writeTag(buf, compound);
    }
}
