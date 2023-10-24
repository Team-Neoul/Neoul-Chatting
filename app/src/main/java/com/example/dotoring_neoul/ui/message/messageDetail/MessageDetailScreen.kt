package com.example.dotoring_neoul.ui.message.messageDetail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropScaffoldState
import androidx.compose.material.BackdropValue
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dotoring.R
import com.example.dotoring_neoul.ui.message.messageBox.MessageBox
import com.example.dotoring_neoul.ui.message.messageBox.MessageListItem
import com.example.dotoring_neoul.ui.theme.DotoringTheme
import com.example.dotoring_neoul.ui.theme.Gray
import com.example.dotoring_neoul.ui.theme.Green
import com.example.dotoring_neoul.ui.theme.Navy
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


/**
 * 쪽지함 상세
 *
 * BackdropScaffold를 이용하여 대화내역과 텍스트입력 및 전송을 나누어 구현
 * 버튼으로 BackdropValue인 Revealed, Concealed를 조정
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MessageDetailScreen(messageDetailViewModel: MessageDetailViewModel = viewModel(), navController: NavHostController) {
//    val roomPk = roomInfo.roomPK
    Log.d("메시지", " 메시지 실행" + 1)
    messageDetailViewModel.renderMessageDetailScreen(navController, 1)
    Log.d("메시지", " 메시지?? 실행" + 1)
    var message by remember { mutableStateOf("") }
    val messageDetailUiState by messageDetailViewModel.uiState.collectAsState()
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed)
    val chatlist = messageDetailUiState.chatList

    BackdropScaffold(
        scaffoldState = scaffoldState,
        backLayerBackgroundColor = Color.White,
        peekHeight = 200.dp,
        headerHeight = 0.dp,
        modifier = Modifier,
        appBar = {},
        backLayerContent = {
            Box() {
                Column(modifier = Modifier) {
                    Surface(
                        modifier = Modifier
                            .height(80.dp)
                            .fillMaxWidth()
                            .shadow(
                                elevation = 10.dp,
                                spotColor = Color(0x99000000),
                                ambientColor = Color(0x50000000)
                            )
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(15.dp)
                        ) {
                            TextButton(onClick = { /*TODO*/ },
                                modifier=Modifier.width(40.dp),) {
                                Text(modifier = Modifier
                                    .fillMaxSize()
                                    .align(Alignment.Bottom), text = "〈", fontSize = 20.sp, color = Color.Gray)

                            }
                            Column(
                                modifier = Modifier.align(Alignment.CenterVertically)
                            ) {
                                Text(text = "닉네임", color= Navy, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
                                Text(text = "학과", color= Color.Gray)
                            }
                        }
                    }

                    Column(
                        Modifier
                            .background(Color.White)
                            .padding(horizontal = 20.dp, vertical = 7.dp)) {
                        val scrollState = rememberLazyListState()
                        MentoChatBox(messageDetail = MessageDetail(
                            nickname = "닉네임",
                            letterId = 1,
                            content = "내     ㄴ마넝마니어마니어나미어ㅏㅁ니아ㅣㄴ언미암니용",
                            writer = true,
                            createdAt = "2020.02.10"
                        ))
                        MentoChatBox(messageDetail = MessageDetail(
                            nickname = "닉네임",
                            letterId = 1,
                            content = "내용 \n 엥",
                            writer = true,
                            createdAt = "2020.02.10"
                        ))
                        MentiChatBox(messageDetail = MessageDetail(
                            nickname = "닉네임",
                            letterId = 1,
                            content = "내용",
                            writer = true,
                            createdAt = "2020.02.10"
                        ))
                        MentiChatBox(messageDetail = MessageDetail(
                            nickname = "닉네임",
                            letterId = 1,
                            content = "내용 \n 내용내요아앙만어ㅣㅏㅁ어ㅣ마ㅓ이멍미ㅏㅇ미어ㅏㄴ어ㅣ",
                            writer = true,
                            createdAt = "2020.02.10"
                        ))
                        MentoChatBox(messageDetail = MessageDetail(
                            nickname = "닉네임",
                            letterId = 1,
                            content = "내용",
                            writer = true,
                            createdAt = "2020.02.10"
                        ))
                        MentiChatBox(messageDetail = MessageDetail(
                            nickname = "닉네임",
                            letterId = 1,
                            content = "내용",
                            writer = true,
                            createdAt = "2020.02.10"
                        ))
                        MentiChatBox(messageDetail = MessageDetail(
                            nickname = "닉네임",
                            letterId = 1,
                            content = "내용",
                            writer = true,
                            createdAt = "2020.02.10"
                        ))
                        MentiChatBox(messageDetail = MessageDetail(
                            nickname = "닉네임",
                            letterId = 1,
                            content = "내용",
                            writer = true,
                            createdAt = "2020.02.10"
                        ))
                        MentiChatBox(messageDetail = MessageDetail(
                            nickname = "닉네임",
                            letterId = 1,
                            content = "내용",
                            writer = true,
                            createdAt = "2020.02.10"
                        ))
                        MentiChatBox(messageDetail = MessageDetail(
                            nickname = "닉네임",
                            letterId = 1,
                            content = "내용",
                            writer = true,
                            createdAt = "2020.02.10"
                        ))
                        MentiChatBox(messageDetail = MessageDetail(
                            nickname = "닉네임",
                            letterId = 1,
                            content = "내용",
                            writer = true,
                            createdAt = "2020.02.10"
                        ))
                        MentiChatBox(messageDetail = MessageDetail(
                            nickname = "닉네임",
                            letterId = 1,
                            content = "내용",
                            writer = true,
                            createdAt = "2020.02.10"
                        ))
                        MentiChatBox(messageDetail = MessageDetail(
                            nickname = "닉네임",
                            letterId = 1,
                            content = "내용",
                            writer = true,
                            createdAt = "2020.02.10"
                        ))
                        MentiChatBox(messageDetail = MessageDetail(
                            nickname = "닉네임",
                            letterId = 1,
                            content = "내용",
                            writer = true,
                            createdAt = "2020.02.10"
                        ))
                        MentiChatBox(messageDetail = MessageDetail(
                            nickname = "닉네임",
                            letterId = 1,
                            content = "내용 \n 아아",
                            writer = true,
                            createdAt = "2020.02.10"
                        ))
                        MentoChatBox(messageDetail = MessageDetail(
                            nickname = "닉네임",
                            letterId = 1,
                            content = "내용",
                            writer = true,
                            createdAt = "2020.02.10"
                        ))
                        MentoChatBox(messageDetail = MessageDetail(
                            nickname = "닉네임",
                            letterId = 1,
                            content = "내용",
                            writer = true,
                            createdAt = "2020.02.10"
                        ))
                        MentoChatBox(messageDetail = MessageDetail(
                            nickname = "닉네임",
                            letterId = 1,
                            content = "내용",
                            writer = true,
                            createdAt = "2020.02.10"
                        ))
                        MentoChatBox(messageDetail = MessageDetail(
                            nickname = "닉네임",
                            letterId = 1,
                            content = "내용",
                            writer = true,
                            createdAt = "2020.02.10"
                        ))
                        MentoChatBox(messageDetail = MessageDetail(
                            nickname = "닉네임",
                            letterId = 1,
                            content = "내용",
                            writer = true,
                            createdAt = "2020.02.10"
                        ))
//                        LazyColumn(state = scrollState) {
//                            this.items(chatlist) {
//                                    messageDetail ->
//                                if(messageDetail.writer){
//                                    MentoChatBox(messageDetail=messageDetail) }
//                                else{ MentiChatBox(messageDetail=messageDetail) }
                                //MentiChatBox("안녕! 나는 수미야\n하이\n ㅎㅎ")}


                                //id가 멘토면 MentoChatBox, 멘티면 MentiChatBox로 만들어지게끔 구현


//                            }
//                        }
                    }
                }
                Surface(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(bottom = 10.dp)
                        .align(Alignment.BottomEnd)
                ) {
                    MessageField(value = messageDetailUiState.writeContent, onValueChange = {messageDetailViewModel.updateContent(it)}, textField = stringResource(id = R.string.message_textField), navController = navController, scaffoldState = BackdropScaffoldState(
                        BackdropValue.Concealed)
                    )
                }
            }

        },
        frontLayerContent = {
            Box(modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(top = 15.dp, bottom = 45.dp)) {
                Surface(modifier = Modifier
                    .width(100.dp)
                    .height(5.dp)
                    .align(Alignment.TopCenter), shape = RoundedCornerShape(40.dp),
                    color = Color.Gray){}
                Text(modifier = Modifier
                    .padding(top = 30.dp)
                    .align(Alignment.TopCenter), color = Color.Gray,
                    text = stringResource(id = R.string.message_info ), fontSize = 12.sp)
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp, start = 25.dp, end = 25.dp)
                        .align(Alignment.TopCenter)
                        .background(Color.White),
                    shape = RoundedCornerShape(35.dp),
                ) {

                }
                MessageField(value = messageDetailUiState.writeContent,
                    onValueChange = {messageDetailViewModel.updateContent(it)},
                    textField = stringResource(id = R.string.message_textField),
                    navController = navController,
                    scaffoldState = BackdropScaffoldState(
                    BackdropValue.Concealed)
                )

//                Box(
//                    modifier = Modifier
//                        .align(Alignment.BottomEnd)
//                ) {
//                    MessageButton(scaffoldState)
//                }
            }


        }
    )
}

/**
 * 쪽지 작성 textField
 * 송신버튼을 textField 안에 구현
 */
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun MessageField(scaffoldState: BackdropScaffoldState, navController: NavHostController, value:String, onValueChange:(String)->Unit, textField: String, messageDetailViewModel: MessageDetailViewModel = viewModel()) {
    val scope = rememberCoroutineScope()
    val messageDetailUiState by messageDetailViewModel.uiState.collectAsState()

    Surface(modifier = Modifier
        .height(50.dp)
        .padding(horizontal = 15.dp)
        , shape = RoundedCornerShape(10.dp)
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current

        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(
                text = textField,
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Left) },
            colors = TextFieldDefaults.textFieldColors(
                textColor = colorResource(id = R.color.black),
                focusedIndicatorColor = Gray,
                unfocusedIndicatorColor = Gray,
                backgroundColor = Gray,
                placeholderColor = Color.Black),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            })
        )
        Row( modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Button(modifier = Modifier
                .padding(5.dp)
                .width(45.dp)
                .height(45.dp), shape = RoundedCornerShape(10.dp),
                onClick = {
                    messageDetailViewModel.sendMessage(navController)
                    scope.launch { scaffoldState.reveal() }
                }) {
                Image(
                    painter = painterResource(R.drawable.send_active),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }

    }
}



/**
 * textField 안에 들어가는 송신버튼 구현
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MessageButton(scaffoldState: BackdropScaffoldState) {
    val scope = rememberCoroutineScope()
    Button(shape = CircleShape,
        modifier = Modifier
            .size(110.dp)
            .padding(20.dp),
        onClick = {
            if(scaffoldState.currentValue== BackdropValue.Concealed)
            { scope.launch { scaffoldState.reveal() }}
            else
            { scope.launch { scaffoldState.conceal() }}
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = Navy)
    ) {
        if(scaffoldState.currentValue== BackdropValue.Concealed)
        {
            Image(
                painter = painterResource(R.drawable.chatting),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize())
        }
        else{
            Image(
            painter = painterResource(R.drawable.message),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize())
        }}
}

/**
 * 멘티의 대화 내용을 담는 Box
 * 대화 리스트를 불러올 때 Boolean값에 따라 ChatBox 유형이 결정됨
 */
//text: String, name: String, time: String
@Composable
fun MentiChatBox(messageDetailViewModel: MessageDetailViewModel = viewModel(), messageDetail: MessageDetail) {
    val messageDetailUiState by messageDetailViewModel.uiState.collectAsState()

    Row(modifier= Modifier
        .fillMaxWidth()
        .padding(3.dp), horizontalArrangement = Arrangement.End){

        Row(modifier = Modifier.wrapContentWidth().fillMaxWidth(0.8f), Arrangement.End) {
            Column(modifier = Modifier.align(Alignment.Bottom)) {
                if(true){
                    Surface(modifier = Modifier
                        .size(7.dp)
                        .align(Alignment.End), color = Green, shape = RoundedCornerShape(5.dp)) {
                    }}
                Text(
                    modifier = Modifier
                        .padding(bottom = 5.dp),
                    text = messageDetail.createdAt, fontSize = 10.sp, color = Color.Gray
                )
            }

            Surface(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .wrapContentWidth()
                    .padding(start = 5.dp, bottom = 5.dp),
                shape = RoundedCornerShape(10.dp),
                color = Green
            ) {
                Text(
                    modifier = Modifier.padding(10.dp).wrapContentWidth(),
                    text = messageDetail.content,
                    color = Color.Black
                )
            }

        }
        Surface(modifier = Modifier
            .align(Alignment.CenterVertically)
            .width(10.dp)
            .height(5.dp), color = Green, shape = RoundedCornerShape(5.dp)
        ) {
            
        }
    }
}

/**
 * 멘토의 대화 내용을 담는 Box
 * 대화 리스트를 불러올 때 Boolean값에 따라 ChatBox 유형이 결정됨
 */
@Composable
fun MentoChatBox(messageDetailViewModel: MessageDetailViewModel = viewModel(), messageDetail: MessageDetail) {
    val messageDetailUiState by messageDetailViewModel.uiState.collectAsState()

    Row(Modifier, horizontalArrangement = Arrangement.Center) {
        Image(
            painter = painterResource(R.drawable.home_profile_sample),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(end = 5.dp)
                .clip(CircleShape)
                .size(45.dp),
            alignment = Alignment.Center
        )
        Surface(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .width(10.dp)
                .height(5.dp), color = Gray, shape = RoundedCornerShape(5.dp)
        ) {
        }
        Row(modifier = Modifier.wrapContentWidth().fillMaxWidth(0.8f)) {
            Surface(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .wrapContentWidth()
                    .padding(end = 5.dp, bottom = 5.dp),
                shape = RoundedCornerShape(10.dp),
                color = Gray
            ) {
                Text(
                    modifier = Modifier.padding(10.dp).wrapContentWidth(),
                    text = messageDetail.content,
                    color = Color.Black
                )
            }
            Column(modifier = Modifier.align(Alignment.Bottom)) {
                Text(
                    modifier = Modifier
                        .padding(bottom = 5.dp),
                    text = messageDetail.createdAt, fontSize = 10.sp, color = Color.Gray
                )
            }
        }
    }
}




@Preview(showSystemUi = true)
@Composable
fun MessageDetailPreview() {
    DotoringTheme {
        MessageDetailScreen(navController = rememberNavController())
    }
}