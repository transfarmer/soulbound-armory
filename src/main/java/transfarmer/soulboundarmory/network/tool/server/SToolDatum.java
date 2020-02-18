package transfarmer.soulboundarmory.network.tool.server;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import transfarmer.soulboundarmory.capability.tool.ISoulTool;
import transfarmer.soulboundarmory.capability.tool.SoulToolProvider;
import transfarmer.soulboundarmory.data.tool.SoulToolDatum;
import transfarmer.soulboundarmory.data.tool.SoulToolType;
import transfarmer.soulboundarmory.network.tool.client.CToolDatum;

public class SToolDatum implements IMessage {
    private int value;
    private int datumIndex;
    private int typeIndex;

    public SToolDatum() {}

    public SToolDatum(final int value, final SoulToolDatum datum, final SoulToolType type) {
        this.value = value;
        this.datumIndex = datum.index;
        this.typeIndex = type.index;
    }

    @Override
    public void fromBytes(final ByteBuf buffer) {
        this.value = buffer.readInt();
        this.datumIndex = buffer.readInt();
        this.typeIndex = buffer.readInt();
    }

    @Override
    public void toBytes(final ByteBuf buffer) {
        buffer.writeInt(this.value);
        buffer.writeInt(this.datumIndex);
        buffer.writeInt(this.typeIndex);
    }

    public static final class Handler implements IMessageHandler<SToolDatum, IMessage> {
        @Override
        public IMessage onMessage(SToolDatum message, MessageContext context) {
            final EntityPlayer player = Minecraft.getMinecraft().player;
            final ISoulTool instance = SoulToolProvider.get(player);
            final SoulToolDatum datum = SoulToolDatum.getDatum(message.datumIndex);
            final SoulToolType type = SoulToolType.getType(message.typeIndex);

            instance.addDatum(message.value, datum, type);

            return new CToolDatum(message.value, datum, type);
        }
    }
}