package com.cec.zbgl.utils;

import com.cec.zbgl.dto.CourseDto;
import com.cec.zbgl.dto.DeviceDto;
import com.cec.zbgl.dto.DeviceReleDto;
import com.cec.zbgl.dto.OrgnizationDto;
import com.cec.zbgl.dto.UserDto;
import com.cec.zbgl.model.DeviceCourse;
import com.cec.zbgl.model.DeviceInfo;
import com.cec.zbgl.model.DeviceRele;
import com.cec.zbgl.model.SpOrgnization;
import com.cec.zbgl.model.User;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;


public class DtoUtils {

    public static DeviceInfo toDevice(DeviceDto deviceDto) throws UnsupportedEncodingException {
        DeviceInfo device = new DeviceInfo();
//        final BeanCopier copier = BeanCopier.create(DeviceDto.class,DeviceInfo.class, false);
//        copier.copy(deviceDto,device,null);
            device.setmId(deviceDto.getId());
            device.setType(deviceDto.getType());
            if (deviceDto.getName() != null){
                device.setName(new String(deviceDto.getName()));
            }
            if (deviceDto.getLocation() != null) {
                device.setLocation(new String(deviceDto.getLocation()));
            }

            device.setCount(deviceDto.getCount());
            device.setStatus(deviceDto.getStatus());
            device.setBelongSys(deviceDto.getBelongSys());
            if (deviceDto.getDescription() != null) {
                device.setDescription(new String(deviceDto.getDescription()));
            }

            device.setCreaterId(deviceDto.getCreaterId());
            device.setCreateTime(deviceDto.getCreateTime());
            if (deviceDto.getCreaterName() != null) {
                device.setCreaterName(new String(deviceDto.getCreaterName()));
            }

            device.setImage(deviceDto.getImage());
            device.setValid(deviceDto.isValid());
            device.setUpload(true);
            device.setEdited(false);

        return device;
    }

    public static DeviceDto toDeviceDto(DeviceInfo device) {
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setId(device.getmId());
        deviceDto.setType(device.getType());
        deviceDto.setName(device.getName());
        deviceDto.setLocation(device.getLocation());
        deviceDto.setCount(device.getCount());
        deviceDto.setStatus(device.getStatus());
        deviceDto.setBelongSys(device.getBelongSys());
        deviceDto.setDescription(device.getDescription());
        deviceDto.setCreateTime(device.getCreateTime());
        deviceDto.setCreaterName(device.getCreaterName());
        deviceDto.setImage(device.getImage());
        deviceDto.setValid(device.isValid());
        deviceDto.setUpload(device.isUpload());
        return deviceDto;
    }

    public static CourseDto toCourseDto(DeviceCourse course) {
        CourseDto courseDto = new CourseDto();
        courseDto.setId(course.getmId());
        courseDto.setDeviceId(course.getDeviceId());
        courseDto.setName(course.getName());
        courseDto.setSysId(course.getSysId());
        courseDto.setDeviceType(course.getDeviceType());
        courseDto.setCourseType(course.getCourseType());
        courseDto.setCreaterId(course.getCreaterId());
        courseDto.setCreateTime(course.getCreateTime());
        courseDto.setCreaterName(course.getCreaterName());
        courseDto.setCreaterId(course.getCreaterId());
        courseDto.setLocation(course.getLocation());
        courseDto.setDescription(course.getDescription());
        courseDto.setValid(course.isValid());
        courseDto.setUpload(course.isUpload());
        courseDto.setImage(course.getImage());
        courseDto.setImage_full(course.getImage_full());
        return courseDto;

    }

    public static DeviceCourse toCourse(CourseDto courseDto) {
        DeviceCourse course = new DeviceCourse();
        course.setmId(courseDto.getId());
        course.setName(courseDto.getName());
        course.setSysId(courseDto.getSysId());
        course.setDeviceId(courseDto.getDeviceId());
        course.setDeviceType(courseDto.getDeviceType());
        course.setCourseType(courseDto.getCourseType());
        course.setCreaterId(courseDto.getCreaterId());
        course.setCreaterName(courseDto.getCreaterName());
        course.setCreateTime(courseDto.getCreateTime());
        course.setLocation(courseDto.getLocation());
        course.setDescription(courseDto.getDescription());
        course.setValid(courseDto.isValid());
        course.setUpload(courseDto.isUpload());
        course.setImage(courseDto.getImage());
        course.setImage_full(courseDto.getImage_full());
        course.setUpload(courseDto.isUpload());
        course.setEdited(false);
        return course;
    }

    public static SpOrgnization toOrgnization(OrgnizationDto orgDto) {
        SpOrgnization org = new SpOrgnization();
        org.setmId(orgDto.getId());
        org.setCode(orgDto.getCode());
        org.setParentCode(orgDto.getParentCode());
        org.setValue(orgDto.getValue());
        org.setName(orgDto.getName());
        org.setSys(orgDto.isSys());
        org.setCreateTime(orgDto.getCreateTime());
        org.setCreaterId(orgDto.getCreaterId());
        org.setValid(orgDto.isValid());
        org.setDescription(orgDto.getDescription());
        org.setImage(orgDto.getImage());
        org.setUpload(false);
        return org;
    }


    public static OrgnizationDto toOrgDto(SpOrgnization org) {
        OrgnizationDto orgDto = new OrgnizationDto();
        orgDto.setId(org.getmId());
        orgDto.setCode(org.getCode());
        orgDto.setParentCode(org.getParentCode());
        orgDto.setValue(org.getValue());
        orgDto.setName(org.getName());
        orgDto.setSys(org.isSys());
        orgDto.setCreateTime(org.getCreateTime());
        orgDto.setCreaterId(org.getCreaterId());
        orgDto.setValid(org.isValid());
        orgDto.setDescription(org.getDescription());
        orgDto.setImage(org.getImage());
        orgDto.setUpload(org.isUpload());
        return orgDto;
    }

    public static User toUser(UserDto userDto) {
        User user = new User();
        user.setmId(userDto.getId());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setAppUpdate(userDto.isAppUpdate());
        user.setEdited(false);
        return user;
    }

    public static UserDto toUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getmId());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        userDto.setAppUpdate(user.isAppUpdate());
        return userDto;
    }

    public static DeviceRele toRele(DeviceReleDto releDto) {
        DeviceRele rele = new DeviceRele();
        rele.setmId(releDto.getId());
        rele.setDeviceId(releDto.getDeviceId());
        rele.setReleDeviceId(releDto.getReleDeviceId());
        rele.setCreateTime(releDto.getCreateTime());
        rele.setValid(releDto.isValid());
        return rele;

    }

    public static DeviceReleDto toReleDto(DeviceRele deviceRele) {
        DeviceReleDto releDto = new DeviceReleDto();
        releDto.setId(deviceRele.getmId());
        releDto.setDeviceId(deviceRele.getDeviceId());
        releDto.setReleDeviceId(deviceRele.getReleDeviceId());
        releDto.setCreateTime(deviceRele.getCreateTime());
        releDto.setValid(deviceRele.isValid());
        return releDto;
    }


    public static CourseDto toCourseDto_short(DeviceCourse course) {
        CourseDto courseDto = new CourseDto();
        courseDto.setId(course.getmId());
        courseDto.setCreateTime(course.getCreateTime());
        courseDto.setValid(course.isValid());
        return courseDto;
    }

    public static DeviceDto toDeviceDto_short(DeviceInfo device) {
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setId(device.getmId());
        deviceDto.setCreateTime(device.getCreateTime());
        deviceDto.setValid(device.isValid());
        return deviceDto;
    }


/*    public static Blob bytetoBlob(byte[] bytes) {
        //creat a new blob
        Blob blob = null;
        try {
            blob = new SerialBlob(bytes);
            //set start is 1
            //这个setBytes 是指定起点，然后设定字节
            blob.setBytes(1, bytes);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //pub byte 这个方法是添加byte
//          blob.putBytes(1, result);

        return blob;

    }*/

    /*public static byte[] blobtoByte(Blob blob) {
        BufferedInputStream bin = null;

        try {
            bin = new BufferedInputStream(blob.getBinaryStream());
            byte[] bytes = new byte[(int) blob.length()];
            int len = bytes.length;
            int offset = 0;
            int read = 0;
            while (offset < len && (read = bin.read(bytes,offset,len - offset)) >=0) {
                offset += read;
            }
            return bytes;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
                try {
                    bin.close();
                    bin = null;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }

        }

    }*/


}
