package org.jurassicraft.server.message;

import io.netty.buffer.ByteBuf;
import net.ilexiconn.llibrary.server.network.AbstractMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.server.dinosaur.Dinosaur;
import org.jurassicraft.server.entity.base.JCEntityRegistry;
import org.jurassicraft.server.entity.item.PaddockSignEntity;

public class PlacePaddockSignMessage extends AbstractMessage<PlacePaddockSignMessage>
{
    private int dino;
    private BlockPos pos;
    private int x;
    private int y;
    private int z;
    private EnumFacing facing;

    public PlacePaddockSignMessage()
    {
    }

    public PlacePaddockSignMessage(EnumFacing facing, BlockPos pos, Dinosaur dino)
    {
        this.dino = JCEntityRegistry.getDinosaurId(dino);
        this.pos = new BlockPos(x, y, z);
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
        this.facing = facing;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onClientReceived(Minecraft client, PlacePaddockSignMessage message, EntityPlayer player, MessageContext messageContext)
    {

    }

    @Override
    public void onServerReceived(MinecraftServer server, PlacePaddockSignMessage message, EntityPlayer player, MessageContext messageContext)
    {
        World world = player.worldObj;

        EnumFacing side = message.facing;
        BlockPos pos = message.pos;

        PaddockSignEntity paddockSign = new PaddockSignEntity(world, pos, side, message.dino);

        if (player.canPlayerEdit(pos, side, player.getHeldItem()) && paddockSign.onValidSurface())
        {
            world.spawnEntityInWorld(paddockSign);

            if (!player.capabilities.isCreativeMode)
            {
                InventoryPlayer inventory = player.inventory;
                inventory.decrStackSize(inventory.currentItem, 1);
            }
        }
    }

    @Override
    public void toBytes(ByteBuf buffer)
    {
        buffer.writeInt(x);
        buffer.writeInt(y);
        buffer.writeInt(z);
        buffer.writeInt(dino);
        buffer.writeByte((byte) facing.getIndex());
    }

    @Override
    public void fromBytes(ByteBuf buffer)
    {
        x = buffer.readInt();
        y = buffer.readInt();
        z = buffer.readInt();
        dino = buffer.readInt();
        facing = EnumFacing.getFront(buffer.readByte());
        pos = new BlockPos(x, y, z);
    }
}
