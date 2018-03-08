package buildcraft.transport.plug;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.EnumFacing;

import buildcraft.api.facades.IFacadePhasedState;
import buildcraft.api.facades.IFacadeState;

import buildcraft.lib.misc.MessageUtil;
import buildcraft.lib.misc.NBTUtilBC;
import buildcraft.lib.net.PacketBufferBC;

public class FacadePhasedState implements IFacadePhasedState {
    public final FacadeBlockStateInfo stateInfo;
    public final boolean isHollow;
    @Nullable
    public final EnumDyeColor activeColour;

    public FacadePhasedState(FacadeBlockStateInfo stateInfo, boolean isHollow, EnumDyeColor activeColour) {
        this.stateInfo = stateInfo;
        this.isHollow = isHollow;
        this.activeColour = activeColour;
    }

    public static FacadePhasedState readFromNbt(NBTTagCompound nbt) {
        FacadeBlockStateInfo stateInfo = FacadeStateManager.defaultState;
        if (nbt.hasKey("state")) {
            try {
                IBlockState blockState = NBTUtil.readBlockState(nbt.getCompoundTag("state"));
                stateInfo = FacadeStateManager.validFacadeStates.get(blockState);
                if (stateInfo == null) {
                    stateInfo = FacadeStateManager.defaultState;
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        boolean isHollow = nbt.getBoolean("isHollow");
        EnumDyeColor colour = NBTUtilBC.readEnum(nbt.getTag("activeColour"), EnumDyeColor.class);
        return new FacadePhasedState(stateInfo, isHollow, colour);
    }

    public NBTTagCompound writeToNbt() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("state", NBTUtil.writeBlockState(new NBTTagCompound(), stateInfo.state));
        nbt.setBoolean("isHollow", isHollow);
        if (activeColour != null) {
            nbt.setTag("activeColour", NBTUtilBC.writeEnum(activeColour));
        }
        return nbt;
    }

    public static FacadePhasedState readFromBuffer(PacketBufferBC buf) {
        IBlockState state = MessageUtil.readBlockState(buf);
        boolean isHollow = buf.readBoolean();
        EnumDyeColor colour = MessageUtil.readEnumOrNull(buf, EnumDyeColor.class);
        FacadeBlockStateInfo info = FacadeStateManager.validFacadeStates.get(state);
        if (info == null) {
            info = FacadeStateManager.defaultState;
        }
        return new FacadePhasedState(info, isHollow, colour);
    }

    public void writeToBuffer(PacketBufferBC buf) {
        MessageUtil.writeBlockState(buf, stateInfo.state);
        buf.writeBoolean(isHollow);
        MessageUtil.writeEnumOrNull(buf, activeColour);
    }

    public FacadePhasedState withSwappedIsHollow() {
        return new FacadePhasedState(stateInfo, !isHollow, activeColour);
    }

    public FacadePhasedState withColour(EnumDyeColor colour) {
        return new FacadePhasedState(stateInfo, isHollow, colour);
    }

    public boolean isSideSolid(EnumFacing side) {
        return stateInfo.isSideSolid[side.ordinal()];
    }

    // IFacadePhasedState

    @Override
    public IFacadeState getState() {
        return stateInfo;
    }

    @Override
    public boolean isHollow() {
        return isHollow;
    }

    @Override
    public EnumDyeColor getActiveColor() {
        return activeColour;
    }
}