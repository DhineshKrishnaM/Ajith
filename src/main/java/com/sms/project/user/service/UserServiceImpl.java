package com.sms.project.user.service;

import com.sms.project.user.entity.User;
import com.sms.project.user.entity.UserDto;
import com.sms.project.user.repo.UserRepo;
import com.sms.project.utility.errorcode.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<User> getAllUsersList() {

        List<User> userList = userRepo.findAll();
        return userList.stream().filter(user -> user.getDeleted_at()==null).collect(Collectors.toList());
    }

    @Override
    public String deleteUserById(int id) {
        User getUser = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException(ErrorCodes.USER_NOT_FOUND));
        getUser.setDeleted_at(LocalDate.now());
        userRepo.saveAndFlush(getUser);
        log.info("Deleted user");
        return "User Details deleted";
    }

    @Override
    public UserDto saveUser(UserDto user) {
        Boolean isValid = validateCredentials(user);
        if (!isValid) throw new IllegalArgumentException(ErrorCodes.EMAIL_INVALID);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        if (userRepo.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException(ErrorCodes.EMAIL_ALREADY_REGISTERED);
        }
        User userDet = modelMapper.map(user, User.class);
        User userDt = userRepo.save(userDet);
        return modelMapper.map(userDt, UserDto.class);
    }

    @Override
    public UserDto updateUserDetails(int userId, UserDto userDto) {
        log.info("User Details updating....");
        User userProfile = userRepo.findById(userId).orElseThrow(() -> new IllegalArgumentException(ErrorCodes.USER_NOT_FOUND));
        userProfile.setUsername(userDto.getUsername());
        userProfile.setEmail(userDto.getEmail());
        userProfile.setPassword(userDto.getPassword());
        userRepo.save(userProfile);
        return modelMapper.map(userProfile, UserDto.class);
    }

    @Override
    public UserDto getUserById(int userId) {
        log.info(userId+ " ID is fetching details...");
        User userProfile = userRepo.findById(userId).get();
        return modelMapper.map(userProfile,UserDto.class);
//        return userProfile;
    }

    private Boolean validateCredentials(UserDto user) {
        String regexEmail = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        if (!Pattern.compile(regexEmail, Pattern.CASE_INSENSITIVE).matcher((user.getEmail())).find()) {
            throw new IllegalArgumentException(ErrorCodes.EMAIL_INVALID);
        }
        String password = user.getPassword();
        String regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        if (!Pattern.compile(regexp).matcher(password).find()) {
            throw new IllegalArgumentException(ErrorCodes.PASSWORD_INVALID + " Password should be minimum 8 character, " +
                    " It contains at least one uppercase, one lowercase and one number");
        }
        return true;
    }
}
