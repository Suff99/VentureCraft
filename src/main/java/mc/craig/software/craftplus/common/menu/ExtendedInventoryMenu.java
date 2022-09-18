package mc.craig.software.craftplus.common.menu;

import com.mojang.datafixers.util.Pair;
import mc.craig.software.craftplus.common.capability.ExtendedInventoryCapability;
import mc.craig.software.craftplus.common.capability.IExtendedInventory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class ExtendedInventoryMenu extends InventoryMenu {

    public static boolean PREVENT_SLOTS = false;
    public static final Component CONTAINER_TITLE = Component.translatable("container.minecraft_plus.extended_inventory");
    private static final EquipmentSlot[] SLOT_IDS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
    static final ResourceLocation[] TEXTURE_EMPTY_SLOTS = new ResourceLocation[]{EMPTY_ARMOR_SLOT_BOOTS, EMPTY_ARMOR_SLOT_LEGGINGS, EMPTY_ARMOR_SLOT_CHESTPLATE, EMPTY_ARMOR_SLOT_HELMET};

    public ExtendedInventoryMenu(Inventory pPlayerInventory, boolean pActive, final Player pOwner) {
        super(pPlayerInventory, pActive, pOwner);
        PREVENT_SLOTS = false;

        IExtendedInventory extendedInventory = pPlayerInventory.player.getCapability(ExtendedInventoryCapability.CAPABILITY).resolve().orElseThrow();

        // Main Inv
        for (int row = 0; row < 4; ++row) {
            for (int col = 0; col < 11; ++col) {
                this.addSlot(new Slot(pPlayerInventory, col + (row + 1) * 11, 87 + 8 + col * 18, 216 + 8 + row * 18));
            }
        }

        // Hotbar
        for (int col = 0; col < 11; ++col) {
            this.addSlot(new Slot(pPlayerInventory, col, 87 + 8 + col * 18, 300));
        }

        // Armor
        for (int k = 0; k < 4; ++k) {
            final EquipmentSlot equipmentslot = SLOT_IDS[k];
            this.addSlot(new Slot(pPlayerInventory, 11 * 5 + 3 - k, 116, 6 + k * 36) {
                /**
                 * Helper method to put a stack in the slot.
                 */
                public void set(ItemStack stack) {
                    ItemStack itemstack = this.getItem();
                    super.set(stack);
                    pOwner.onEquipItem(equipmentslot, itemstack, stack);
                }

                /**
                 * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1 in
                 * the case of armor slots)
                 */
                public int getMaxStackSize() {
                    return 1;
                }

                /**
                 * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
                 */
                public boolean mayPlace(ItemStack pStack) {
                    return pStack.canEquip(equipmentslot, pOwner);
                }

                /**
                 * Return whether this slot's stack can be taken from this slot.
                 */
                public boolean mayPickup(Player pPlayer) {
                    ItemStack itemstack = this.getItem();
                    return (itemstack.isEmpty() || pPlayer.isCreative() || !EnchantmentHelper.hasBindingCurse(itemstack)) && super.mayPickup(pPlayer);
                }

                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                    return Pair.of(InventoryMenu.BLOCK_ATLAS, TEXTURE_EMPTY_SLOTS[equipmentslot.getIndex()]);
                }
            });
        }
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }
}
