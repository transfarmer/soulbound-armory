package transfarmer.soulboundarmory.network.C2S;

import net.fabricmc.fabric.api.network.PacketContext;
import transfarmer.soulboundarmory.network.common.ComponentPacket;
import transfarmer.soulboundarmory.network.common.ExtendedPacketBuffer;

public class C2SBindSlot extends ComponentPacket {
    @Override
    protected void accept(final PacketContext context, final ExtendedPacketBuffer buffer) {
        super.accept(context, buffer);

        final int slot = buffer.readInt();

        if (this.component.getBoundSlot() == slot) {
            this.component.unbindSlot();
        } else {
            this.component.bindSlot(slot);
        }

        this.component.sync();
        this.component.refresh();
    }
}
