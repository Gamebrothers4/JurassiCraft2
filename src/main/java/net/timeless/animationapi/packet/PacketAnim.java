package net.timeless.animationapi.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;

public class PacketAnim implements IMessage
{
    private byte animID;
    private int entityID;

    public PacketAnim()
    {
    }

    public PacketAnim(byte anim, int entity)
    {
        animID = anim;
        entityID = entity;
    }

    @Override
    public void toBytes(ByteBuf buffer)
    {
        buffer.writeByte(animID);
        buffer.writeInt(entityID);
    }

    @Override
    public void fromBytes(ByteBuf buffer)
    {
        animID = buffer.readByte();
        entityID = buffer.readInt();
    }

    public static class Handler implements IMessageHandler<PacketAnim, IMessage>
    {
        @Override
        public IMessage onMessage(final PacketAnim packet, MessageContext ctx)
        {
        	final EntityPlayer player = JurassiCraft.proxy.getPlayerEntityFromContext(ctx);

        	Minecraft.getMinecraft().addScheduledTask(new Runnable() 
            {
                @Override
                public void run() 
                {        	
                	World world = player.worldObj;
                	EntityDinosaur entity = (EntityDinosaur) world.getEntityByID(packet.entityID);

                    if (entity != null && packet.animID != -1)
                    {
                        entity.setAnimID(packet.animID);
                    }
                }
            });

            return null;
        }
    }
}
