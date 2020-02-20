package transfarmer.soulboundarmory.network.server.weapon;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import transfarmer.soulboundarmory.capability.weapon.SoulWeaponProvider;

public class SWeaponTab implements IMessage {
    int tab;

    public SWeaponTab() {}

    public SWeaponTab(final int tab) {
        this.tab = tab;
    }

    public void fromBytes(final ByteBuf buffer) {
        this.tab = buffer.readInt();
    }

    public void toBytes(final ByteBuf buffer) {
        buffer.writeInt(this.tab);
    }

    public static final class Handler implements IMessageHandler<SWeaponTab, IMessage> {
        @Override
        public IMessage onMessage(SWeaponTab message, MessageContext context) {
            SoulWeaponProvider.get(context.getServerHandler().player).setCurrentTab(message.tab);

            return null;
        }
    }
}