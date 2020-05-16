package transfarmer.soulboundarmory.skill.common;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nonnull;
import transfarmer.soulboundarmory.skill.Skill;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class SkillLeeching extends Skill {
    public SkillLeeching() {
        this(0);
    }

    public SkillLeeching(final int level) {
        super("leeching", new ItemStack(Items.ROTTEN_FLESH),  level, -1);
    }

    @Nonnull
    @Override
    public @Nonnull List<Skill> getDependencies() {
        return new ArrayList<>();
    }

    @Override
    public int getCost() {
        return !this.learned ? 3 : this.level + 1;
    }
}
